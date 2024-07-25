package com.mariesto.orderservice.controller;

import com.mariesto.orderservice.model.CreateOrderRequest;
import com.mariesto.orderservice.model.OrderDetailResponse;
import com.mariesto.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody CreateOrderRequest request) {
        log.info("Create order request: {}", request);
        String orderId = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }

    @PatchMapping(value = "/{orderId}")
    public void cancelOrder(@PathVariable("orderId") final String orderId) {
        orderService.cancelOrder(orderId);
    }

    @GetMapping(value = "/{orderId}")
    public ResponseEntity<OrderDetailResponse> getOrderDetail(@PathVariable("orderId") final String orderId) {
        OrderDetailResponse response = orderService.getOrderDetail(orderId);
        return ResponseEntity.ok().body(response);
    }

}
