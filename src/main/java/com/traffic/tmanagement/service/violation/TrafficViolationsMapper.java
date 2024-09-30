package com.traffic.tmanagement.service.violation;

import com.traffic.tmanagement.dto.PaymentsDTO;
import com.traffic.tmanagement.dto.TrafficViolationsDTO;
import com.traffic.tmanagement.dto.ViolationTypeDTO;
import com.traffic.tmanagement.entity.Payments;
import com.traffic.tmanagement.entity.TrafficViolations;
import com.traffic.tmanagement.entity.ViolationType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TrafficViolationsMapper {
    TrafficViolationsMapper INSTANCE = Mappers.getMapper(TrafficViolationsMapper.class);
    TrafficViolationsDTO trafficViolationsToTrafficViolationsDTO(TrafficViolations trafficViolations);
    TrafficViolations trafficViolationsDTOToTrafficViolations(TrafficViolationsDTO trafficViolationsDTO);

    ViolationTypeDTO violationTypeToViolationTypeDTO(ViolationType violationType);

    ViolationType violationTypeDTOToViolationType(ViolationTypeDTO violationTypeDTO);

    PaymentsDTO paymentsToPaymentsDTO(Payments payments);
    Payments paymentsDTOToPayments(PaymentsDTO paymentsDTO);
    void trafficViolationsDTOToTrafficViolations(TrafficViolationsDTO dto,@MappingTarget TrafficViolations entity);
}
