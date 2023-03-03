package com.example.demo.domain.its.cctv;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class CctvKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "CCTV_ID")
    private String cctvId;
}
