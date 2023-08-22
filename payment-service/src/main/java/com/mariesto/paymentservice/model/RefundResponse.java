package com.mariesto.paymentservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RefundResponse {
    String paymentReferenceId;

    String orderId;

    String paymentStatus;
}
