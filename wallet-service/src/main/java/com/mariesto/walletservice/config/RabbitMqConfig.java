package com.mariesto.walletservice.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class RabbitMqConfig {
    private final RabbitMqConfigProps rabbitMqConfigProps;

    public RabbitMqConfig(RabbitMqConfigProps rabbitMqConfigProps) {
        this.rabbitMqConfigProps = rabbitMqConfigProps;
    }

    @Bean
    public ConnectionFactory amqpConnectionFactory() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(rabbitMqConfigProps.getHost());
        log.info("try to initialize connection factory : {}", connectionFactory);
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

    @Bean
    public Queue topUpQueue() {
        return new Queue(rabbitMqConfigProps.getTopUpQueue(), rabbitMqConfigProps.isDurable(), rabbitMqConfigProps.isExclusive(),
                rabbitMqConfigProps.isAutoDelete());
    }

    @Bean
    public Queue ceditQueue() {
        return new Queue(rabbitMqConfigProps.getCreditQueue(), rabbitMqConfigProps.isDurable(), rabbitMqConfigProps.isExclusive(),
                rabbitMqConfigProps.isAutoDelete());
    }

    @Bean
    public Queue debitQueue() {
        return new Queue(rabbitMqConfigProps.getDebitQueue(), rabbitMqConfigProps.isDurable(), rabbitMqConfigProps.isExclusive(),
                rabbitMqConfigProps.isAutoDelete());
    }

    @Bean
    public Queue userCreateQueue() {
        return new Queue(rabbitMqConfigProps.getWalletCreateQueue(), rabbitMqConfigProps.isDurable(), rabbitMqConfigProps.isExclusive(),
                rabbitMqConfigProps.isAutoDelete());
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(rabbitMqConfigProps.getExchange());
    }

    @Bean
    public Binding topUpBinding() {
        return BindingBuilder.bind(topUpQueue()).to(exchange()).with(rabbitMqConfigProps.getTopUpKey());
    }

    @Bean
    public Binding creditBinding() {
        return BindingBuilder.bind(ceditQueue()).to(exchange()).with(rabbitMqConfigProps.getCreditKey());
    }

    @Bean
    public Binding debitBinding() {
        return BindingBuilder.bind(debitQueue()).to(exchange()).with(rabbitMqConfigProps.getDebitKey());
    }

    @Bean
    public Binding createWalletBinding() {
        return BindingBuilder.bind(userCreateQueue()).to(exchange()).with(rabbitMqConfigProps.getWalletCreateKey());
    }
}
