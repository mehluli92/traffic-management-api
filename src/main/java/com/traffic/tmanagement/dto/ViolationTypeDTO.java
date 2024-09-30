package com.traffic.tmanagement.dto;

import com.traffic.tmanagement.entity.TrafficViolations;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViolationTypeDTO {
    private Long id;
    private String name;
    private String description;
    private Double fineAmount;
//    private TrafficViolations trafficViolations; // Simplified
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
