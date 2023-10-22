package com.mariesto.paymentservice.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;
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
        Message message = MessageBuilder.withBody(SerializationUtils.serialize(data)).setContentType(MessageProperties.CONTENT_TYPE_JSON).build();
        log.info("publish debit message with content : {}", message);
        amqpTemplate.send(topicExchange, debitRoutingKey, message);
    }

    public void publishCreditEvent(PaymentMessage data) {
        //        Message message = MessageBuilder.withBody(SerializationUtils.serialize(data)).setContentType(MessageProperties.CONTENT_TYPE_JSON).build();
        log.info("publish credit message with content : {}", data);
        amqpTemplate.convertAndSend(topicExchange, creditRoutingKey, data);
    }

}
