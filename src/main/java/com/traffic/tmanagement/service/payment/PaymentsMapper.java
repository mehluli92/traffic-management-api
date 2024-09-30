package com.traffic.tmanagement.service.payment;

import com.traffic.tmanagement.dto.PaymentsDTO;
import com.traffic.tmanagement.entity.Payments;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentsMapper {
    Payments paymentsDTOToPayments(PaymentsDTO paymentsDTO);
    PaymentsDTO paymentsToPaymentsDTO(Payments payments);

}
