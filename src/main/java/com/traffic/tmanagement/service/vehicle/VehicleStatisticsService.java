package com.traffic.tmanagement.service.vehicle;

import com.traffic.tmanagement.entity.VehicleStatistics;
import com.traffic.tmanagement.repository.VehicleStatisticsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleStatisticsService {
    private final VehicleStatisticsRepository vehicleStatisticsRepository;

    public VehicleStatisticsService(VehicleStatisticsRepository vehicleStatisticsRepository) {
        this.vehicleStatisticsRepository = vehicleStatisticsRepository;
    }

    public List<VehicleStatistics> getAll() {
        return vehicleStatisticsRepository.findAll();
    }

    public VehicleStatistics createVehicleStatistics(VehicleStatistics vehicleStatistics) {
        return vehicleStatisticsRepository.save(vehicleStatistics);
    }
}
