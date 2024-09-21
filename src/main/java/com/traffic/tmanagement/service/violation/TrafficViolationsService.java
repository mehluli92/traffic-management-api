package com.traffic.tmanagement.service.violation;

import com.traffic.tmanagement.entity.TrafficViolations;
import com.traffic.tmanagement.repository.TrafficViolationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrafficViolationsService {
    private final TrafficViolationsRepository trafficViolationsRepository;

    @Autowired
    public TrafficViolationsService(TrafficViolationsRepository trafficViolationsRepository) {
        this.trafficViolationsRepository = trafficViolationsRepository;
    }

    public List<TrafficViolations> getAllTrafficViolations(){
        return trafficViolationsRepository.findAll();
    }

    public Optional<TrafficViolations> getTrafficViolation(Long id) {
        return trafficViolationsRepository.findById(id);
    }

    public TrafficViolations createTrafficViolation(TrafficViolations trafficViolations) {
        return trafficViolationsRepository.save(trafficViolations);
    }

    public TrafficViolations updateTrafficViolation(Long id, TrafficViolations trafficViolations){
        TrafficViolations oldTrafficViolation = trafficViolationsRepository.findById(id).orElse(null);
        if (oldTrafficViolation == null) {
            return null;
        }
        oldTrafficViolation.setViolationType(trafficViolations.getViolationType());
        oldTrafficViolation.setFine_amount(trafficViolations.getFine_amount());
        oldTrafficViolation.setNumber_plate(trafficViolations.getNumber_plate());
        oldTrafficViolation.setLocation(trafficViolations.getLocation());
        oldTrafficViolation.setLatitude(trafficViolations.getLatitude());
        oldTrafficViolation.setLongitude(trafficViolations.getLongitude());

        trafficViolationsRepository.save(oldTrafficViolation);
        return oldTrafficViolation;
    }

    public void deleteTrafficViolation(Long id) {
        trafficViolationsRepository.deleteById(id);
    }


}
