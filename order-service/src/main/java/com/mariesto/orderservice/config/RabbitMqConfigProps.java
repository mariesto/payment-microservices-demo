package com.mariesto.orderservice.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties (prefix = "rabbit-mq-props")
@ToString
public class RabbitMqConfigProps {
    private String host;

    private boolean durable;

    private boolean exclusive;

    private boolean autoDelete;

    private String exchange;

    private String cancelOrderKey;

}
