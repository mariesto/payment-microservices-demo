package com.mariesto.orderservice.persistence.repository;

import com.mariesto.orderservice.persistence.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findOrderByOrderId(final String orderId);

}
