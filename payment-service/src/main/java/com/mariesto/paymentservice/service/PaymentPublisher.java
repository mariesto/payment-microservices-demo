package com.mariesto.paymentservice.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class PaymentPublisher {
    private final AmqpTemplate amqpTemplate;

    @Value ("${rabbit-mq-props.exchange}")
    private String topicExchange;

    @Value ("${rabbit-mq-props.credit-key}")
    private String creditRoutingKey;

    @Value ("${rabbit-mq-props.debit-key}")
    private String debitRoutingKey;

    public void publishDebitEvent(PaymentMessage data) {
        log.info("publish debit message with content : {}", data);
        amqpTemplate.convertAndSend(topicExchange, debitRoutingKey, data);
    }

    public void publishCreditEvent(PaymentMessage data) {
        log.info("publish credit message with content : {}", data);
        amqpTemplate.convertAndSend(topicExchange, creditRoutingKey, data);
    }

}
