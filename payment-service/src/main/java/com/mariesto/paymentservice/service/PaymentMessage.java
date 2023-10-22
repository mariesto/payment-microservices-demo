package com.mariesto.paymentservice.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class PaymentMessage {
    private String userId;

    private Double amount;

    private String paymentReferenceId;

}
