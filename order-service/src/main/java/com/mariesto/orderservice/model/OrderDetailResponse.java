package com.mariesto.orderservice.model;

import lombok.Data;

@Data
public class OrderDetailResponse {
    private String orderId;
    private String orderStatus;
    private String userId;
}
