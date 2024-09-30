package com.traffic.tmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrafficViolationsDTO {
    private Long id;
    private String location;
    private Double latitude;
    private Double longitude;
    private String numberPlate;
    private String imageUrl;
    private Double fineAmount;
    private String status;
    private ViolationTypeDTO violationType;
    private PaymentsDTO payment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
