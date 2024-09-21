package com.traffic.tmanagement.controller;

import com.traffic.tmanagement.entity.ViolationType;
import com.traffic.tmanagement.service.violation.ViolationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("violation-types")
public class ViolationTypeController {
    private final ViolationTypeService violationTypeService;

    @Autowired
    public ViolationTypeController(ViolationTypeService violationTypeService) {
        this.violationTypeService = violationTypeService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ViolationType>> getAllViolationTypes() {
        List<ViolationType> violationTypes = violationTypeService.getAllViolationTypes();
        return ResponseEntity.ok(violationTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViolationType> getViolationTypeById(@PathVariable Long id) {
        Optional<ViolationType> violationType = violationTypeService.getViolationTypeById(id);
        return violationType.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ViolationType> updateViolationType(@PathVariable Long id, @RequestBody ViolationType violationType){
        ViolationType updatedViolationType = violationTypeService.updateViolationType(id, violationType);
        return updatedViolationType != null ? ResponseEntity.ok(updatedViolationType): ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteViolationType(@PathVariable Long id) {
        violationTypeService.deleteViolationType(id);
        return ResponseEntity.noContent().build();
    }
}
