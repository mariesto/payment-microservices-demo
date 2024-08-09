package com.mariesto.orderservice.service;

import com.mariesto.orderservice.constant.OrderStatus;
import com.mariesto.orderservice.model.CreateOrderRequest;
import com.mariesto.orderservice.model.OrderDetailResponse;
import com.mariesto.orderservice.persistence.entity.Order;
import com.mariesto.orderservice.persistence.repository.OrderRepository;
import com.mariesto.orderservice.service.event.EventPublisher;
import com.mariesto.orderservice.service.event.OrderMessage;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final ModelMapper modelMapper;
    private final EventPublisher eventPublisher;

    public String createOrder(CreateOrderRequest request) {
        Order order = new Order();
        order.setOrderId(generateOrderId());
        order.setStatus(OrderStatus.CREATED);
        order.setUserId(request.getUserId());
        order.setTotalPrice(new Random(5).nextDouble());
        repository.save(order);
        return order.getOrderId();
    }

    private String generateOrderId() {
        DecimalFormat sixDigitFormat = new DecimalFormat("000000");
        long sequence = 0; // Replace with a database sequence or synchronized counter

        sequence++;
        String paddedNumber = sixDigitFormat.format(sequence);
        return "ORDER-" + paddedNumber;
    }

    public void cancelOrder(final String orderId) {
        Order order = repository.findOrderByOrderId(orderId);
        Optional.ofNullable(order).ifPresent(o -> {
            o.setStatus(OrderStatus.CANCELED);
            repository.save(o);
        });
        OrderMessage orderMessage = modelMapper.map(order, OrderMessage.class);
        eventPublisher.publishCanceledOrderEvent(orderMessage);
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
