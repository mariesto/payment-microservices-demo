package com.mariesto.walletservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "rabbit-mq")
public class RabbitMqConfigProps {

  private String queueName;

  private boolean durable;

  private boolean exclusive;

  private boolean autoDelete;
}
