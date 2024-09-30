package com.traffic.tmanagement.controller;

import com.traffic.tmanagement.dto.vehicle.VehicleDTO;
import com.traffic.tmanagement.entity.Vehicle;
import com.traffic.tmanagement.service.vehicle.VehicleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<VehicleDTO> createVehicle (@RequestBody VehicleDTO vehicleDTO) {
        VehicleDTO createVehicle = vehicleService.createVehicle(vehicleDTO);
        return ResponseEntity.ok(createVehicle);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable Long id) {
        Optional<VehicleDTO> vehicle = vehicleService.getVehicleById(id);
        return vehicle.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<VehicleDTO>> getAllVehicles(Pageable pageable) {
        Page<VehicleDTO> vehicles = vehicleService.getAllVehicles(pageable);
        return ResponseEntity.ok(vehicles);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable Long id, @RequestBody VehicleDTO vehicleDTO) {
        VehicleDTO updatedVehicle = vehicleService.updateVehicle(id,vehicleDTO);
        return ResponseEntity.ok(updatedVehicle);
    }

    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
