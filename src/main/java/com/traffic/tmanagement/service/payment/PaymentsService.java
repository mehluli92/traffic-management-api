package com.traffic.tmanagement.service.payment;

import com.traffic.tmanagement.entity.Payments;
import com.traffic.tmanagement.repository.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentsService {
    private final PaymentsRepository paymentsRepository;

    @Autowired
    public PaymentsService(PaymentsRepository paymentsRepository) {
        this.paymentsRepository = paymentsRepository;
    }

    public List<Payments> getAllPayments() {
        return paymentsRepository.findAll();
    }

    public Optional<Payments> getPaymentsById(Long id) {
        return paymentsRepository.findById(id);
    }

    public Payments createPayment(Payments payments) {
        return paymentsRepository.save(payments);
    }

    public Payments updatePayments(Long id,Payments newPayments) {
        Payments oldPayment = paymentsRepository.findById(id).orElse(null);
        if (oldPayment != null){
            oldPayment.setPayment_method(newPayments.getPayment_method());
            oldPayment.setAmount_paid(newPayments.getAmount_paid());
            oldPayment.setTrafficViolation(newPayments.getTrafficViolation());
            return paymentsRepository.save(oldPayment);
        } else {
            return null;
        }
    }

    public void deletePayment(Long id) {
        paymentsRepository.deleteById(id);
    }


}
