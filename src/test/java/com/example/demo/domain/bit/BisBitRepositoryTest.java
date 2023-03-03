//package com.example.demo.domain.bit;
//
//import com.example.demo.domain.bis.bit.BisBitRepository;
//import com.example.demo.domain.bis.bit.Bit;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//
//@Transactional("bisTransactionManager")
//@SpringBootTest
//class BisBitRepositoryTest {
//
//    @Autowired
//    BisBitRepository bitRepository;
//
//    @Test
//    void save() {
//        Bit bit = new Bit();
//        bit.setBitId('1');
//
//        Bit result = bitRepository.save(bit);
//
//        Assertions.assertThat(result.getBitId()).isEqualTo(bit.getBitId());
//    }
//}