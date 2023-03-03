package com.example.demo.common.enumClass;

import lombok.Getter;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description : filterDTO의 operator 데이터 제약 처리를 위한 enum class
 * @Modification Information
 *               수정일        수정자          수정내용
 *               ---------- --------- -------------------------------
 * 
 * @author GEONLEE
 * @version 1.0.0
 * @since 2023-02-15
 */
@Getter
public enum Operator {
    EQUAL("eq"), LIKE("contains"), BETWEEN("between");

    private final String type;

    Operator(String type){
        this.type = type;
    }

    public String type(){
        return this.type;
    }

    private static final Map<String, Operator> OPERATOR_MAP = Stream.of(values()).collect(Collectors.toMap(Operator::type, e -> e));
    
    public static Optional<Operator> valueOfOperator(String operator){
        return Optional.ofNullable(OPERATOR_MAP.get(operator)) ;
    }
}
