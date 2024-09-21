package com.traffic.tmanagement.service.violation;

import com.traffic.tmanagement.entity.ViolationType;
import com.traffic.tmanagement.repository.ViolationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViolationTypeService {
    private final ViolationTypeRepository violationTypeRepository;

    @Autowired
    public ViolationTypeService(ViolationTypeRepository violationTypeRepository) {
        this.violationTypeRepository = violationTypeRepository;
    }

    public List<ViolationType> getAllViolationTypes(){
        return violationTypeRepository.findAll();
    }

    public Optional<ViolationType> getViolationTypeById(Long id){
        return Optional.ofNullable(violationTypeRepository.findById(id).orElse(null));
    }

    public ViolationType createViolationType(ViolationType violationType) {
        return violationTypeRepository.save(violationType);
    }

    public ViolationType updateViolationType(Long id, ViolationType violationType) {
        ViolationType oldViolationType = violationTypeRepository.findById(id).orElse(null);
        if (oldViolationType == null) {
            return null;
        }
        oldViolationType.setName(violationType.getName());
        oldViolationType.setDescription(violationType.getDescription());

        violationTypeRepository.save(oldViolationType);
        return oldViolationType;
    }

    public void deleteViolationType(Long id) {
        violationTypeRepository.deleteById(id);
    }
}
