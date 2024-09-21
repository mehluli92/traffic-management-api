package com.traffic.tmanagement.repository;

import com.traffic.tmanagement.entity.ViolationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViolationTypeRepository extends JpaRepository<ViolationType, Long> {
}
