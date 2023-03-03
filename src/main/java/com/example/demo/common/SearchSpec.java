package com.example.demo.common;


import com.example.demo.common.annotation.SearchField;
import com.example.demo.common.enumClass.ColumnType;
import com.example.demo.common.vo.FilterDTO;
import com.example.demo.common.annotation.SearchField;
import com.example.demo.common.enumClass.ColumnType;
import com.example.demo.common.enumClass.Operator;
import com.example.demo.common.enumClass.SortOrder;
import com.example.demo.common.vo.FilterDTO;
import com.example.demo.common.vo.SortDTO;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import javax.persistence.criteria.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description : 조회 처리를 위한 공통 static class
 * @Modification Information
 *               수정일        수정자          수정내용
 *               ---------- --------- -------------------------------
 *               2023-02-14  GEONLEE   (getSearchField) SearchField Annotation을 활용한 조회 메소드로 변경
 *               2023-02-15  GEONLEE   (getSpec) FilterDTO operator를 enum으로 변경한 내용 적용
 *                                     (getSort) SortDTO dir을 enum으로 변경한 내용 적용
 *                                     (getSort) depth 가 있는 컬럼도 정렬되도록 개선
 *
 * @author GEONLEE
 * @version 1.0.0
 * @since 2023-02-10
 */
public class SearchSpec {
    /**
     * Criteria Sort 기능을 위한 Sort 리턴 메소드
     *
     * @author GEONLEE
     * @param <T>
     * @param sortList 정렬정보가 들어있는 ArrayList
     * @return Sort 객체
     * @since 2023-02-10
     */
    public static <T> Sort getSort(Class<T> clazz, ArrayList<SortDTO> sortList) {
        Field[] fields = clazz.getDeclaredFields();
        HashMap<String, String> sortMap = new HashMap<>();
        for (Field f : fields) {
            Annotation[] annotations = f.getDeclaredAnnotations();
            for (Annotation a : annotations) {
                if (a instanceof SearchField) {
                    SearchField sf = (SearchField) a;
                    String[] columnNames = sf.columnName();
                    if (columnNames.length > 1) {
                        Arrays.stream(columnNames).forEach(c -> {
                            String[] cn = c.split("[.]");
                            sortMap.put(cn[cn.length - 1], c);
                        });
                    } else if (columnNames.length == 1) {
                        if (columnNames[0].indexOf(".") != -1) {
                            String[] cn = columnNames[0].split("[.]");
                            sortMap.put(cn[cn.length - 1], columnNames[0]);
                        }else{
                            sortMap.put(f.getName(), columnNames[0]);
                        }
                    }
                }
            }
        }

        Sort sort = null;
        List<Sort.Order> orderList = sortList.stream().map(s -> {
            String dir = s.getDir().toLowerCase();
            String field = sortMap.get(s.getField());
            Optional<SortOrder> order = SortOrder.valueOfOrder(dir);
            if(!order.isPresent()){
                throw new ClassCastException("잘못된 Order가 전달됨. \n"+s.toString());
            }
            switch(order.get()){
                case ASC : return Sort.Order.desc(field);
                case DESC : return Sort.Order.asc(field);
            }
            return Sort.Order.by(null);
        }).collect(Collectors.toList());
        sort = Sort.by(orderList);
        return sort;
    }

    /**
     * Criteria Search 시 활용할 specification을 리턴하는 메소드
     *
     * @author GEONLEE
     * @param <T>        Entity Type
     * @param clazz      Entity Class
     * @param filterList 조회 정보가 담긴 ArrayList
     * @return JPA Specification
     * @since 2023-02-10
     */
    public static <T> Specification<T> getSpec(Class<T> clazz, ArrayList<FilterDTO> filterList) {
        return new Specification<T>() {
            @Override
            @Nullable
            @SuppressWarnings("unchecked")
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                HashMap<String, Path<?>> searchFields = getSearchField(clazz, root);
                ArrayList<Predicate> predicates = new ArrayList<>();
                for (FilterDTO f : filterList) {
                    String v = f.getValue().toLowerCase(), o = f.getOperator().toLowerCase();
                    Path<String> p = (Path<String>) searchFields.get(f.getField());
                    Optional<Operator> oop = Operator.valueOfOperator(o);
                    if(!oop.isPresent()){
                        throw new ClassCastException("잘못된 Operator가 전달됨. \n"+f.toString());
                    }
                    switch(oop.get()){
                        case EQUAL : predicates.add(cb.and(cb.equal(cb.lower(p), v)));
                            break;
                        case LIKE : predicates.add(cb.and(cb.like(cb.lower(p), "%" + v + "%")));
                            break;
                        case BETWEEN :
                            String[] range = f.getValue().split(",");
                            Path<Timestamp> p2 = (Path<Timestamp>) searchFields.get(f.getField());
                            Timestamp st = CmmnVar.strToTimestamp(range[0]);
                            Timestamp ed = CmmnVar.strToTimestamp(range[1]);
                            predicates.add(cb.and(cb.between(p2, st, ed)));
                            break;
                    }
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    /**
     * depth가 있는 컬럼의 path를 찾아 hashmap에 추가하는 메소드
     *
     * @author GEONLEE
     * @param <T>          Entity Type
     * @param root         Criteria Root
     * @param columnNames  column name fields
     * @param searchFields HashMap<String, Path<?>> path를 저장할 자료구조
     * @since 2023-02-14
     */
    private static <T> void findAndSetPath(Root<T> root, String[] columnNames, HashMap<String, Path<?>> searchFields) {
        Arrays.stream(columnNames).map(cn -> cn.split("[.]")).forEach(c -> {
            Path<?> path = null;
            path = root.get(c[0]);
            for (int i = 1, n = c.length; i < n; i++) {
                path = path.get(c[i]);
            }
            searchFields.put(c[c.length - 1], path);
        });
    }

    /**
     * 조회 할 필드명을 키로 path를 값으로 갖는 hashmap 리턴 메소드
     *
     * @author GEONLEE
     * @param <T>   Entity type
     * @param clazz Entity Class
     * @param root  Criteria Root
     * @return HashMap<String, Path<?>>
     * @since 2023-02-14
     */
    private static <T> HashMap<String, Path<?>> getSearchField(Class<T> clazz, Root<T> root) {
        Field[] fields = clazz.getDeclaredFields();
        HashMap<String, Path<?>> searchFields = new HashMap<>();
        for (Field f : fields) {
            Annotation[] annotations = f.getDeclaredAnnotations();
            for (Annotation a : annotations) {
                if (a instanceof SearchField) {
                    SearchField sf = (SearchField) a;
                    String[] columnNames = sf.columnName();
                    ColumnType dt = sf.columnType();
                    if (columnNames.length > 1) {
                        findAndSetPath(root, columnNames, searchFields);
                    } else if (columnNames.length == 1) {
                        if (columnNames[0].indexOf(".") != -1) {
                            findAndSetPath(root, columnNames, searchFields);
                        } else {
                            searchFields.put(f.getName(), root.get(columnNames[0]));
                        }
                    }
                }
            }
        }
        return searchFields;
    }
}
