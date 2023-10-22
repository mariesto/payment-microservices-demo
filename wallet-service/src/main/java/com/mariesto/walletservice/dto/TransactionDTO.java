package com.mariesto.walletservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionDTO {
    private String userId;

    private Double amount;

    private String paymentReferenceId;

}
