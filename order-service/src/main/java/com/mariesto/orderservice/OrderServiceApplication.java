package com.mariesto.orderservice;

import com.mariesto.orderservice.config.RabbitMqConfig;
import com.mariesto.orderservice.config.RabbitMqConfigProps;
import com.mariesto.orderservice.controller.OrderController;
import com.mariesto.orderservice.persistence.repository.OrderRepository;
import com.mariesto.orderservice.service.OrderService;
import com.mariesto.orderservice.service.event.EventPublisher;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableConfigurationProperties(value = RabbitMqConfigProps.class)
@SpringBootApplication
@EnableJpaRepositories
@ComponentScan(basePackageClasses = { OrderController.class, OrderService.class, OrderRepository.class, EventPublisher.class, RabbitMqConfig.class })
public class OrderServiceApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

}
