package com.traffic.tmanagement.dto.vehicle;

import com.traffic.tmanagement.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {
    private Long id;
    private String licensePlate;
    private String make;
    private String model;
    private Long year;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserDTO user;  // Reference to UserDTO to represent the relationship
}
