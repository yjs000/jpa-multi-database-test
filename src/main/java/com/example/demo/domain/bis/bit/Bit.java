package com.example.demo.domain.bis.bit;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(schema = "ATMSADM", name = "T_MS_BIT")
public class Bit {

    @Id
    @Column(name = "BIT_ID")
    private Character bitId;

    @Column(name = "BIT_NM")
    private String bitNm;

}
