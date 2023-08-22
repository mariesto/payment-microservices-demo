package com.mariesto.paymentservice.service;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class PaymentMessage implements Serializable {

    private String userId;

    private Double amount;

    private String paymentReferenceId;

}
