package com.example.demo.domain.its.cctv;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(schema = "ATMSADM", name = "T_MS_CCTV")
public class Cctv {

    @Id
    @Column(name = "CCTV_ID")
    private String cctvId;

    @Column(name = "NODE_ID")
    private String nodeId;

    @Column(name = "CTRL_ID")
    private String ctrlId;

    @Column(name = "SUBSTANCE_ID")
    private String substanceId;

    @Column(name = "MATRIXIN_ID")
    private String matrixinId;

    @Column(name = "EQUIP_LOCATN")
    private String equipLocatn;

    @Column(name = "EQUIP_DT")
    private Date equipDt;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_DT")
    private Date endDt;

    @Column(name = "STATUS_CYCL")
    private String statusCycl;

    @Column(name = "MODEL_NM")
    private String modelNm;

    @Column(name = "MNFCT_COMP")
    private String mnfctComp;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "SUBSTANCE_COMP")
    private String substanceComp;


    @Column(name = "SUBSTANCE_MODEL")
    private String substanceModel;


    @Column(name = "SCREEN_DISP")
    private String screenDisp;


    @Column(name = "ENG_SCREEN_DISP")
    private String eng_screenDisp;

    @Column(name = "USE_YN")
    private String useYn;

    @Column(name = "PANTILT_COMP")
    private String pantiltComp;

    @Column(name = "PANTILT_MODEL")
    private String pantiltModel;

    @Column(name = "OPTIC_COMP")
    private String opticComp;

    @Column(name = "OPTIC_MODEL")
    private String opticModel;

    @Column(name = "EQUIP_HEIGHT")
    private String equipHeight;

    @Column(name = "POSX")
    private String posx;

    @Column(name = "POSY")
    private String posy;

    @Column(name = "ETC")
    private String etc;

    @Column(name = "CCTV_URL")
    private String cctvUrl;

}
