package com.example.demo.common.vo;

import lombok.Data;

import java.util.ArrayList;

/**
 * @Description : 조회를 위한 공통 Generic class VO, builder 패턴은 binding 안됨.
 * @Modification Information
 *   수정일        수정자               수정내용
 *  ----------   ---------   -------------------------------                
 * 
 * @author GEONLEE
 * @version 1.0.0
 * @since 2022-11-15
 */
@Data
public class SearchDTO {
    private Integer take;
    private Integer skip;
    private ArrayList<SortDTO> sort;
    private ArrayList<FilterDTO> filter;
}
