package com.traffic.tmanagement.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
