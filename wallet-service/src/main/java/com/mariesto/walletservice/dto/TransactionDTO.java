package com.mariesto.walletservice.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class TransactionDTO implements Serializable {

    private String userId;

    private Double amount;

    private String customerPaymentId;

}
