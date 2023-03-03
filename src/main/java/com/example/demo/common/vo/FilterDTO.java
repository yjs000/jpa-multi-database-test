package com.example.demo.common.vo;

import lombok.Data;

@Data
public class FilterDTO {
    private String field;
    private String value;
    private String operator;
}
