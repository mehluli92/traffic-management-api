package com.traffic.tmanagement.controller;

import com.traffic.tmanagement.entity.Payments;
import com.traffic.tmanagement.service.payment.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public ResponseEntity<Payments> createPayment(@RequestBody Payments payments) {
        return ResponseEntity.ok(paymentsService.createPayment(payments));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payments> getPaymentById(@PathVariable Long id) {
        Optional<Payments> payment = paymentsService.getPaymentsById(id);
        return payment.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payments> updatePayment(@PathVariable Long id, @RequestBody Payments paymentDetails) {
        try {
            Payments updatedPayment = paymentsService.updatePayments(id, paymentDetails);
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
