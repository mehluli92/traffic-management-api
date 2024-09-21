package com.traffic.tmanagement.controller;

import com.traffic.tmanagement.entity.TrafficViolations;
import com.traffic.tmanagement.service.violation.TrafficViolationsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/violations")
public class TrafficViolationsController {
    private final TrafficViolationsService trafficViolationsService;

    public TrafficViolationsController(TrafficViolationsService trafficViolationsService) {
        this.trafficViolationsService = trafficViolationsService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<TrafficViolations>> getTrafficViolations(){
        List<TrafficViolations> trafficViolations = trafficViolationsService.getAllTrafficViolations();
        return ResponseEntity.ok(trafficViolations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrafficViolations> getTrafficViolationById(@PathVariable Long id) {
        Optional<TrafficViolations> trafficViolation = trafficViolationsService.getTrafficViolation(id);
        return trafficViolation.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TrafficViolations> updateTrafficViolations(@PathVariable Long id, @RequestBody TrafficViolations trafficViolations) {
        TrafficViolations updatedTrafficViolation = trafficViolationsService.updateTrafficViolation(id, trafficViolations);
        return updatedTrafficViolation != null ? ResponseEntity.ok(updatedTrafficViolation): ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrafficViolation(@PathVariable Long id) {
        trafficViolationsService.deleteTrafficViolation(id);
        return ResponseEntity.noContent().build();
    }
}
