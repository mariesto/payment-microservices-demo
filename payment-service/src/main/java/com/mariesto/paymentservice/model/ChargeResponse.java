package com.mariesto.paymentservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChargeResponse {
    private String paymentReferenceId;

    private String orderId;

    private String status;

}
