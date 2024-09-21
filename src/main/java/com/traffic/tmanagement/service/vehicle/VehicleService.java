package com.traffic.tmanagement.service.vehicle;

import com.traffic.tmanagement.entity.Vehicle;
import com.traffic.tmanagement.exception.notFound.NotFoundException;
import com.traffic.tmanagement.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle updateVehicle(Long id, Vehicle vehicle) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);

        if(optionalVehicle.isPresent()) {
            Vehicle existingVehicle = optionalVehicle.get();
            existingVehicle.setLicense_plate(vehicle.getLicense_plate());
            existingVehicle.setMake(vehicle.getMake());
            existingVehicle.setModel(vehicle.getModel());
            existingVehicle.setYear(vehicle.getYear());

            return vehicleRepository.save(existingVehicle);
        } else {
         throw new NotFoundException("Vehicle not found with id: " + id);
        }
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
}
