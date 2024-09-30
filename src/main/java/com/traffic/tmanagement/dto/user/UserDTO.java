package com.traffic.tmanagement.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "email is mandatory")
    @Email(message = "email should be valid")
    private String email;
    private String password;
    @NotBlank(message = "Phone number must not be blank")
    private String phone;
    private String ecNumber;
    private Boolean enabled;

    private Set<RoleDTO> roles; // Mapping roles to RoleDTO
    private Set<PermissionDTO> permissions; // Mapping permissions to PermissionDTO
    //private List<VehicleDTO> vehicles; // List of associated VehicleDTOs

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
