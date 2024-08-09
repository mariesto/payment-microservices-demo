package com.mariesto.orderservice.service.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class EventPublisher {

    private final AmqpTemplate amqpTemplate;

    @Value("${rabbit-mq-props.exchange}")
    private String topicExchange;

    public void publishCanceledOrderEvent(Object data) {
        log.info("publish canceled order message with content : {}", data);
        amqpTemplate.convertAndSend(topicExchange, "order.cancel", data);
    }

}
