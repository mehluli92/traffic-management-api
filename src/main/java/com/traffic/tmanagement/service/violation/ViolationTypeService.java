package com.traffic.tmanagement.service.violation;

import com.traffic.tmanagement.dto.ViolationTypeDTO;
import com.traffic.tmanagement.entity.ViolationType;
import com.traffic.tmanagement.repository.ViolationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ViolationTypeService {
    private final ViolationTypeRepository violationTypeRepository;
    private final ViolationTypeMapper violationTypeMapper;

    @Autowired
    public ViolationTypeService(ViolationTypeRepository violationTypeRepository, ViolationTypeMapper violationTypeMapper) {
        this.violationTypeRepository = violationTypeRepository;
        this.violationTypeMapper = violationTypeMapper;
    }

    public Page<ViolationTypeDTO> getAllViolationTypes(Pageable pageable){

        return violationTypeRepository.findAll(pageable)
                .map(violationTypeMapper::violationTypeToViolationTypeDTO);
    }

    public ViolationTypeDTO createViolationType(ViolationTypeDTO violationTypeDTO) {
        ViolationType violationType = violationTypeMapper.violationTypeDTOToViolationType(violationTypeDTO);
        violationType = violationTypeRepository.save(violationType);
        return violationTypeMapper.violationTypeToViolationTypeDTO(violationType);
    }

    public Optional<ViolationTypeDTO> getViolationTypeById(Long id){
        return violationTypeRepository.findById(id)
                .map(violationTypeMapper::violationTypeToViolationTypeDTO);
    }


    public ViolationTypeDTO updateViolationType(Long id, ViolationTypeDTO violationTypeDTO) {
        ViolationType oldViolationType = violationTypeRepository.findById(id).orElse(null);
        if (oldViolationType == null) {
            return null;
        }
        oldViolationType.setName(violationTypeDTO.getName());
        oldViolationType.setDescription(violationTypeDTO.getDescription());

        violationTypeRepository.save(oldViolationType);
        return violationTypeMapper.violationTypeToViolationTypeDTO(oldViolationType);
    }

    public void deleteViolationType(Long id) {
        violationTypeRepository.deleteById(id);
    }
}
