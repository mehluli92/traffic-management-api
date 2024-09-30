package com.traffic.tmanagement.controller;

import com.traffic.tmanagement.dto.TrafficViolationsDTO;
import com.traffic.tmanagement.service.violation.TrafficViolationsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/violations")
public class TrafficViolationsController {
    private final TrafficViolationsService trafficViolationsService;

    public TrafficViolationsController(TrafficViolationsService trafficViolationsService) {
        this.trafficViolationsService = trafficViolationsService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<TrafficViolationsDTO>> getTrafficViolations(Pageable pageable) {
        Page<TrafficViolationsDTO> trafficViolations = trafficViolationsService.getAllTrafficViolations(pageable);
        return ResponseEntity.ok(trafficViolations);
    }

    @PostMapping
    public ResponseEntity<TrafficViolationsDTO> createTrafficViolation(@RequestBody TrafficViolationsDTO trafficViolationsDTO) {
        TrafficViolationsDTO trafficViolation = trafficViolationsService.createTrafficViolation(trafficViolationsDTO);
        return ResponseEntity.ok(trafficViolation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrafficViolationsDTO> getTrafficViolationById(@PathVariable Long id) {
        return trafficViolationsService.getTrafficViolation(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TrafficViolationsDTO> updateTrafficViolations(@PathVariable Long id, @RequestBody TrafficViolationsDTO trafficViolationsDTO) {
        TrafficViolationsDTO updatedTrafficViolation = trafficViolationsService.updateTrafficViolation(id, trafficViolationsDTO);
        return updatedTrafficViolation != null ? ResponseEntity.ok(updatedTrafficViolation) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrafficViolation(@PathVariable Long id) {
        trafficViolationsService.deleteTrafficViolation(id);
        return ResponseEntity.noContent().build();
    }
}
