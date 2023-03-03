package com.example.demo.domain.bis.bit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BisBitRepository extends JpaRepository<Bit, Character>, JpaSpecificationExecutor<Bit> {
}
