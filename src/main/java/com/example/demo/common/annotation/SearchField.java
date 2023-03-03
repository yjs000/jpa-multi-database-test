package com.example.demo.common.annotation;

import com.example.demo.common.enumClass.ColumnType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description : JPA Criteria 에서 사용할 조회 컬럼 설정용 Annotation
 * @Modification Information
 *   수정일        수정자               수정내용
 *  ----------   ---------   ------------------------------- 
 * 
 * @author GEONLEE
 * @version 1.0.0
 * @since 2023-02-14
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SearchField {
    String[] columnName();
    ColumnType columnType() default ColumnType.STRING;
}
