package com.mariesto.orderservice.service.event;

import com.mariesto.orderservice.constant.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderMessage {
    private String orderId;

    private OrderStatus orderStatus;
}
