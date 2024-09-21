package com.traffic.tmanagement.controller;

import com.traffic.tmanagement.entity.Audit;
import com.traffic.tmanagement.service.user.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuditController {
    private final AuditService auditService;

    @Autowired
    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    public ResponseEntity<List<Audit>> getAllAudits() {
        List<Audit> audits = auditService.getAllAudits();
        return ResponseEntity.ok(audits);
    }
}
