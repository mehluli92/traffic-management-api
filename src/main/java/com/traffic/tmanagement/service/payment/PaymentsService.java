package com.traffic.tmanagement.service.payment;

import com.traffic.tmanagement.dto.PaymentsDTO;
import com.traffic.tmanagement.entity.Payments;
import com.traffic.tmanagement.entity.TrafficViolations;
import com.traffic.tmanagement.repository.PaymentsRepository;
import com.traffic.tmanagement.repository.TrafficViolationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentsService {
    private final PaymentsRepository paymentsRepository;
    private final TrafficViolationsRepository  trafficViolationsRepository;
    private final PaymentsMapper paymentsMapper;

    @Autowired
    public PaymentsService(PaymentsRepository paymentsRepository, TrafficViolationsRepository trafficViolationsRepository, PaymentsMapper paymentsMapper) {
        this.paymentsRepository = paymentsRepository;
        this.trafficViolationsRepository = trafficViolationsRepository;
        this.paymentsMapper = paymentsMapper;
    }

    public Page<PaymentsDTO> getAllPayments(Pageable pageable) {
        Pageable sortedByIdDesc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending());
        return paymentsRepository.findAll(sortedByIdDesc).map(paymentsMapper::paymentsToPaymentsDTO);
    }

    public Optional<PaymentsDTO> getPaymentsById(Long id) {
        return paymentsRepository.findById(id).map(paymentsMapper::paymentsToPaymentsDTO);
    }

    public PaymentsDTO createPayment(PaymentsDTO paymentsDTO) {
        Payments newPayment = paymentsMapper.paymentsDTOToPayments(paymentsDTO);

        if(paymentsDTO.getTrafficViolationsDTO().getId() != null){
            TrafficViolations trafficViolations = trafficViolationsRepository.findById(paymentsDTO.getTrafficViolationsDTO().getId())
                    .orElseThrow(()-> new RuntimeException("Traffic Violation not found"));
            newPayment.setTrafficViolations(trafficViolations);
        }
        Payments savedPayment = paymentsRepository.save(newPayment);
        return paymentsMapper.paymentsToPaymentsDTO(savedPayment);
    }

    public PaymentsDTO updatePayments(Long id,PaymentsDTO paymentsDTO) {
        Payments oldPayment = paymentsRepository.findById(id).orElse(null);
        if (oldPayment != null){
            oldPayment.setPayment_method(paymentsDTO.getPaymentMethod());
            oldPayment.setAmount_paid(paymentsDTO.getAmountPaid());
            //oldPayment.setTrafficViolation(paymentsDTO.trafficViolation());
            if(paymentsDTO.getTrafficViolationsDTO().getId() != null){
                TrafficViolations trafficViolations = trafficViolationsRepository.findById(paymentsDTO.getTrafficViolationsDTO().getId())
                        .orElseThrow(() -> new RuntimeException("Traffic Violation not found"));
                oldPayment.setTrafficViolations(trafficViolations);
            }
            Payments updatedPayments = paymentsRepository.save(oldPayment);
            return paymentsMapper.paymentsToPaymentsDTO(updatedPayments);
        } else {
            return null;
        }
    }

    public void deletePayment(Long id) {
        paymentsRepository.deleteById(id);
    }


}
