package com.traffic.tmanagement.controller;

import com.traffic.tmanagement.entity.VehicleStatistics;
import com.traffic.tmanagement.service.vehicle.VehicleStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VehicleStatisticsController {
    private final VehicleStatisticsService vehicleStatisticsService;

    @Autowired
    public VehicleStatisticsController(VehicleStatisticsService vehicleStatisticsService) {
        this.vehicleStatisticsService = vehicleStatisticsService;
    }

    public ResponseEntity<List<VehicleStatistics>> getAllVehicleStatistics(){
        List<VehicleStatistics> vehicleStatistics = vehicleStatisticsService.getAll();
        return ResponseEntity.ok(vehicleStatistics);
    }
}
