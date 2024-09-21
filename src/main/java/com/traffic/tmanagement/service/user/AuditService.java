package com.traffic.tmanagement.service.user;

import com.traffic.tmanagement.entity.Audit;
import com.traffic.tmanagement.repository.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuditService {
    private final AuditRepository auditRepository;

    @Autowired
    public AuditService(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    public List<Audit> getAllAudits() {
        return auditRepository.findAll();
    }

    public Audit createAudit(
            String username,
            String entity_type,
            String previous_state,
            String current_state,
            String action,
            String ip_address,
            String device_info
            ) {
        Audit newAudit = new Audit();
        newAudit.setUsername(username);
        newAudit.setEntity_type(entity_type);
        newAudit.setPrevious_state(previous_state);
        newAudit.setCurrent_state(current_state);
        newAudit.setAction(action);
        newAudit.setIp_address(ip_address);
        newAudit.setDevice_info(device_info);

        return newAudit;
    }
}
