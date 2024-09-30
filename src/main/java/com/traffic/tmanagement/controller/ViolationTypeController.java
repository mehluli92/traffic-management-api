package com.traffic.tmanagement.controller;

import com.traffic.tmanagement.dto.ViolationTypeDTO;
import com.traffic.tmanagement.entity.ViolationType;
import com.traffic.tmanagement.service.violation.ViolationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @PostMapping
    public ResponseEntity<ViolationTypeDTO> createViolationType(@RequestBody ViolationTypeDTO newViolationType) {
        ViolationTypeDTO violationType = violationTypeService.createViolationType(newViolationType);
        return ResponseEntity.ok(violationType);
    }
    @GetMapping("/get-all")
    public ResponseEntity<Page<ViolationTypeDTO>> getAllViolationTypes(Pageable pageable) {
        Page<ViolationTypeDTO> violationTypes = violationTypeService.getAllViolationTypes(pageable);
        return ResponseEntity.ok(violationTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViolationTypeDTO> getViolationTypeById(@PathVariable Long id) {
        Optional<ViolationTypeDTO> violationType = violationTypeService.getViolationTypeById(id);
        return violationType.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ViolationTypeDTO> updateViolationType(@PathVariable Long id, @RequestBody ViolationTypeDTO violationType){
        ViolationTypeDTO updatedViolationType = violationTypeService.updateViolationType(id, violationType);
        return updatedViolationType != null ? ResponseEntity.ok(updatedViolationType): ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteViolationType(@PathVariable Long id) {
        violationTypeService.deleteViolationType(id);
        return ResponseEntity.noContent().build();
    }
}
