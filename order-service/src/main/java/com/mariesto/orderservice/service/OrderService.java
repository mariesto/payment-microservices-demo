package com.mariesto.orderservice.service;

import com.mariesto.orderservice.constant.OrderStatus;
import com.mariesto.orderservice.model.CreateOrderRequest;
import com.mariesto.orderservice.model.OrderDetailResponse;
import com.mariesto.orderservice.persistence.entity.Order;
import com.mariesto.orderservice.persistence.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final ModelMapper modelMapper;

    public String createOrder(CreateOrderRequest request) {
        Order order = new Order();
        order.setOrderId("order-" + new Random().nextInt()); // TODO: use generator
        order.setStatus(OrderStatus.CREATED);
        order.setUserId(request.getUserId());
        order.setTotalPrice(new Random(5).nextDouble());
        repository.save(order);
        return order.getOrderId();
    }

    public void cancelOrder(final String orderId) {
        Order order = repository.findOrderByOrderId(orderId);
        Optional.ofNullable(order).ifPresent(o -> {
            o.setStatus(OrderStatus.CANCELED);
            repository.save(o);
        });
    }

    public OrderDetailResponse getOrderDetail(String orderId) {
        Order order = repository.findOrderByOrderId(orderId);
        Optional<Order> optional = Optional.ofNullable(order);
        if (optional.isEmpty()) {
            throw new RuntimeException("Order Not Found");
        }
        return modelMapper.map(optional.get(), OrderDetailResponse.class);
    }
}
