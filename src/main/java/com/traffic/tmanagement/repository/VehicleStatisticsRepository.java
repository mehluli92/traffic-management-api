package com.traffic.tmanagement.repository;

import com.traffic.tmanagement.entity.VehicleStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleStatisticsRepository extends JpaRepository<VehicleStatistics, Long> {
}
