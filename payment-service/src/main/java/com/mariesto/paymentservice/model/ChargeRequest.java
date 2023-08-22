package com.mariesto.paymentservice.model;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChargeRequest {
    @NotNull
    private String orderId;

    @NotNull
    private String userId;

    @NotNull
    private Double amount;

}
