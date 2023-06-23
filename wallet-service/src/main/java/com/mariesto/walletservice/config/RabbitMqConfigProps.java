package com.mariesto.walletservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "rabbit-mq-props")
public class RabbitMqConfigProps {

  private String host;

  private boolean durable;

  private boolean exclusive;

  private boolean autoDelete;

  private String topUpQueue;

  private String creditQueue;

  private String debitQueue;

  private String walletCreateQueue;

  private String exchange;

  private String topUpKey;

  private String creditKey;

  private String debitKey;

  private String walletCreateKey;
}
