package com.example.demo.domain.its.cctv;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CctvRepository extends JpaRepository<Cctv, CctvKey>, JpaSpecificationExecutor<Cctv> {
}
