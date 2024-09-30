package com.traffic.tmanagement.service.vehicle;

import com.traffic.tmanagement.dto.vehicle.VehicleDTO;
import com.traffic.tmanagement.entity.User;
import com.traffic.tmanagement.entity.Vehicle;
import com.traffic.tmanagement.exception.notFound.NotFoundException;
import com.traffic.tmanagement.repository.UserRepository;
import com.traffic.tmanagement.repository.VehicleRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;
    private final VehicleMapper vehicleMapper;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository, UserRepository userRepository, VehicleMapper vehicleMapper) {
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
        this.vehicleMapper = vehicleMapper;
    }

    public VehicleDTO createVehicle(VehicleDTO vehicleDTO) {
       User user = userRepository.findById(vehicleDTO.getUser().getId())
               .orElseThrow(() -> new NotFoundException("User not found with id : " + vehicleDTO.getUser().getId()));
       Vehicle vehicle = vehicleMapper.vehicleDTOToVehicle(vehicleDTO);
       vehicle.setUser(user);
       Vehicle savedVehicle = vehicleRepository.save(vehicle);
       return vehicleMapper.vehicleToVehicleDTO(savedVehicle);
    }

    public Optional<VehicleDTO> getVehicleById(Long id) {
        return vehicleRepository.findById(id).map(vehicleMapper::vehicleToVehicleDTO);
    }

    public Page<VehicleDTO> getAllVehicles(Pageable pageable) {
        return vehicleRepository.findAll(pageable).map(vehicleMapper::vehicleToVehicleDTO);
    }

    public VehicleDTO updateVehicle(Long id, VehicleDTO vehicleDTO) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);

        if(optionalVehicle.isPresent()) {
            Vehicle existingVehicle = optionalVehicle.get();
            existingVehicle.setLicense_plate(vehicleDTO.getLicensePlate());
            existingVehicle.setMake(vehicleDTO.getMake());
            existingVehicle.setModel(vehicleDTO.getModel());
            existingVehicle.setYear(vehicleDTO.getYear());

            if (vehicleDTO.getUser() != null){
                User user = userRepository.findById(vehicleDTO.getUser().getId())
                        .orElseThrow(() -> new NotFoundException("User not found with id :"+ vehicleDTO.getUser().getId()));
                existingVehicle.setUser(user);
            }

            Vehicle savedVehicle = vehicleRepository.save(existingVehicle);

            return vehicleMapper.vehicleToVehicleDTO(savedVehicle);
        } else {
         throw new NotFoundException("Vehicle not found with id: " + id);
        }
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
}
