package com.traffic.tmanagement.service.vehicle;

import com.traffic.tmanagement.dto.user.UserDTO;
import com.traffic.tmanagement.dto.vehicle.VehicleDTO;
import com.traffic.tmanagement.entity.User;
import com.traffic.tmanagement.entity.Vehicle;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    VehicleDTO vehicleToVehicleDTO(Vehicle vehicle);
    Vehicle vehicleDTOToVehicle(VehicleDTO vehicleDTO);
    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);
}
