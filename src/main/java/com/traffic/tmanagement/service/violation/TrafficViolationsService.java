package com.traffic.tmanagement.service.violation;

import com.traffic.tmanagement.dto.PaymentsDTO;
import com.traffic.tmanagement.dto.TrafficViolationsDTO;
import com.traffic.tmanagement.entity.Payments;
import com.traffic.tmanagement.entity.TrafficViolations;
import com.traffic.tmanagement.entity.ViolationType;
import com.traffic.tmanagement.repository.PaymentsRepository;
import com.traffic.tmanagement.repository.TrafficViolationsRepository;
import com.traffic.tmanagement.repository.ViolationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrafficViolationsService {
    private final TrafficViolationsRepository trafficViolationsRepository;
    private final ViolationTypeRepository violationTypeRepository;
    private final TrafficViolationsMapper trafficViolationsMapper;
    private final PaymentsRepository paymentsRepository;
    @Autowired
    public TrafficViolationsService(TrafficViolationsRepository trafficViolationsRepository, ViolationTypeRepository violationTypeRepository, TrafficViolationsMapper trafficViolationsMapper, PaymentsRepository paymentsRepository) {
        this.trafficViolationsRepository = trafficViolationsRepository;
        this.violationTypeRepository = violationTypeRepository;
        this.trafficViolationsMapper = trafficViolationsMapper;
        this.paymentsRepository = paymentsRepository;
    }

    public Page<TrafficViolationsDTO> getAllTrafficViolations(Pageable pageable) {
        return trafficViolationsRepository.findAll(pageable)
                .map(trafficViolationsMapper::trafficViolationsToTrafficViolationsDTO);
    }

    public Optional<TrafficViolationsDTO> getTrafficViolation(Long id) {
        return trafficViolationsRepository.findById(id)
                .map(trafficViolationsMapper::trafficViolationsToTrafficViolationsDTO);
    }

    public TrafficViolationsDTO createTrafficViolation(TrafficViolationsDTO trafficViolationsDTO) {
        TrafficViolations trafficViolations = trafficViolationsMapper.trafficViolationsDTOToTrafficViolations(trafficViolationsDTO);

        ViolationType violationType = violationTypeRepository.findById(trafficViolationsDTO.getViolationType().getId()).orElse(null);
        if (violationType != null) {
            trafficViolations.setViolationType(violationType);
        }
        TrafficViolations savedViolation = trafficViolationsRepository.save(trafficViolations);
        return trafficViolationsMapper.trafficViolationsToTrafficViolationsDTO(savedViolation);
    }

    public TrafficViolationsDTO updateTrafficViolation(Long id, TrafficViolationsDTO trafficViolationsDTO) {
        TrafficViolations oldTrafficViolation = trafficViolationsRepository.findById(id).orElse(null);
        if (oldTrafficViolation == null) {
            return null;
        }

        trafficViolationsMapper.trafficViolationsDTOToTrafficViolations(trafficViolationsDTO, oldTrafficViolation);

        ViolationType violationType = violationTypeRepository.findById(trafficViolationsDTO.getViolationType().getId()).orElse(null);
        if (violationType != null) {
            oldTrafficViolation.setViolationType(violationType);
        }

        if (trafficViolationsDTO.getPayment() != null) {
            Payments payment = trafficViolationsMapper.paymentsDTOToPayments(trafficViolationsDTO.getPayment());
            oldTrafficViolation.setPayment(payment);
        }

        trafficViolationsRepository.save(oldTrafficViolation);
        return trafficViolationsMapper.trafficViolationsToTrafficViolationsDTO(oldTrafficViolation);
    }

    public void deleteTrafficViolation(Long id) {
        trafficViolationsRepository.deleteById(id);
    }

}
