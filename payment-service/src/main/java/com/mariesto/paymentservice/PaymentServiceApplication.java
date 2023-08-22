package com.mariesto.paymentservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;
import com.mariesto.paymentservice.config.RabbitMqConfig;
import com.mariesto.paymentservice.config.RabbitMqConfigProps;
import com.mariesto.paymentservice.controller.PaymentController;
import com.mariesto.paymentservice.persistence.repository.PaymentRepository;
import com.mariesto.paymentservice.service.PaymentPublisher;
import com.mariesto.paymentservice.service.PaymentService;

@EnableConfigurationProperties (value = RabbitMqConfigProps.class)
@EnableJpaRepositories
@SpringBootApplication
@ComponentScan (basePackageClasses = { PaymentController.class, PaymentService.class, PaymentRepository.class, PaymentPublisher.class, RabbitMqConfig.class })
public class PaymentServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
