package com.traffic.tmanagement.service.violation;

import com.traffic.tmanagement.dto.ViolationTypeDTO;
import com.traffic.tmanagement.entity.ViolationType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ViolationTypeMapper {
    ViolationTypeMapper INSTANCE = Mappers.getMapper(ViolationTypeMapper.class);
    ViolationTypeDTO violationTypeToViolationTypeDTO(ViolationType violationType);
    ViolationType violationTypeDTOToViolationType(ViolationTypeDTO violationTypeDTO);
}
