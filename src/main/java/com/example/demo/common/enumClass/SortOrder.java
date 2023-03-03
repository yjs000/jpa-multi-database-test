package com.example.demo.common.enumClass;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description : SortDTO의 dir 데이터 제약 처리를 위한 enum class
 * @Modification Information
 *               수정일        수정자          수정내용
 *               ---------- --------- -------------------------------
 * 
 * @author GEONLEE
 * @version 1.0.0
 * @since 2023-02-15
 */
public enum SortOrder {
    ASC("asc"), DESC("desc");

    private final String direction;

    SortOrder(String direction){
        this.direction = direction;
    }

    public String direction(){
        return this.direction;
    }

    private static final Map<String, SortOrder> ORDER_MAP = Stream.of(values()).collect(Collectors.toMap(SortOrder::direction, e -> e));
    
    public static Optional<SortOrder> valueOfOrder(String direction){
        return Optional.ofNullable(ORDER_MAP.get(direction)) ;
    }
}
