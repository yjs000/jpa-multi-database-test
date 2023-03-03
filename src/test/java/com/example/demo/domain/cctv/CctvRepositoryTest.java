//package com.example.demo.domain.cctv;
//
//import com.example.demo.domain.its.cctv.Cctv;
//import com.example.demo.domain.its.cctv.CctvKey;
//import com.example.demo.domain.its.cctv.CctvRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//
//@Transactional
//@SpringBootTest
//class CctvRepositoryTest {
//
//    @Autowired
//    CctvRepository cctvRepository;
//
//    @Test
//    void save() {
//        Cctv cctv = new Cctv();
//        CctvKey key = new CctvKey();
//        key.setCctvId("1");
//        cctv.setKey(key);
//
//        Cctv result = cctvRepository.save(cctv);
//
//        Assertions.assertThat(result.getKey().getCctvId())
//                .isEqualTo(cctv.getKey().getCctvId());
//    }
//}