package com.mariesto.paymentservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
@ConfigurationProperties (prefix = "rabbit-mq-props")
public class RabbitMqConfigProps {

    private String host;

    private boolean durable;

    private boolean exclusive;

    private boolean autoDelete;

    private String exchange;

    private String topUpKey;

    private String creditKey;

    private String debitKey;

}
