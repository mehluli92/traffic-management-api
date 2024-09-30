package com.traffic.tmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentsDTO {
    private Long id;
    private Double amountPaid;
    private String paymentMethod;
    private TrafficViolationsDTO trafficViolationsDTO;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
