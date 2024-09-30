package com.traffic.tmanagement.controller;

import com.traffic.tmanagement.dto.PaymentsDTO;
import com.traffic.tmanagement.entity.Payments;
import com.traffic.tmanagement.service.payment.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/payments")
public class PaymentsController {
    private final PaymentsService paymentsService;

    @Autowired
    public PaymentsController(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @GetMapping
    public Page<PaymentsDTO> getAllPayments(Pageable pageable){
        return paymentsService.getAllPayments(pageable);
    }
    @PostMapping
    public ResponseEntity<PaymentsDTO> createPayment(@RequestBody PaymentsDTO paymentsDTO) {
        return ResponseEntity.ok(paymentsService.createPayment(paymentsDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentsDTO> getPaymentById(@PathVariable Long id) {
        Optional<PaymentsDTO> payment = paymentsService.getPaymentsById(id);
        return payment.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentsDTO> updatePayment(@PathVariable Long id, @RequestBody PaymentsDTO paymentsDTO) {
        try {
            PaymentsDTO updatedPayment = paymentsService.updatePayments(id, paymentsDTO);
            return ResponseEntity.ok(updatedPayment);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentsService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
