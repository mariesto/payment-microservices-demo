package com.mariesto.paymentservice.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class RabbitMqConfig {
    private final RabbitMqConfigProps rabbitMqConfigProps;

    public RabbitMqConfig(RabbitMqConfigProps rabbitMqConfigProps) {
        this.rabbitMqConfigProps = rabbitMqConfigProps;
    }

    @Bean
    public ConnectionFactory amqpConnectionFactory() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(rabbitMqConfigProps.getHost());
        log.info("try to initialize connection factory : {}", connectionFactory.toString());
        return connectionFactory;
    }

    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(amqpConnectionFactory());
        log.info("cachingConnectionFactory created : {} with host : {}", cachingConnectionFactory, cachingConnectionFactory.getHost());
        return cachingConnectionFactory;
    }

    @Bean
    public AmqpTemplate amqpTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

}
