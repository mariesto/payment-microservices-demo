package com.mariesto.walletservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.mariesto.walletservice.config.RabbitMqConfig;
import com.mariesto.walletservice.config.RabbitMqConfigProps;
import com.mariesto.walletservice.controller.WalletController;
import com.mariesto.walletservice.persistence.repository.WalletRepository;
import com.mariesto.walletservice.persistence.repository.WalletTransactionsRepository;
import com.mariesto.walletservice.service.WalletService;
import com.mariesto.walletservice.service.strategy.CreditStrategy;
import com.mariesto.walletservice.service.strategy.DebitStrategy;
import com.mariesto.walletservice.service.strategy.TopUpStrategy;
import com.mariesto.walletservice.service.listener.WalletListener;

@EnableConfigurationProperties (value = RabbitMqConfigProps.class)
@EnableJpaRepositories
@ComponentScan (basePackageClasses = { WalletController.class, WalletService.class, CreditStrategy.class, DebitStrategy.class, TopUpStrategy.class, WalletRepository.class, WalletTransactionsRepository.class, RabbitMqConfig.class, WalletListener.class })
@SpringBootApplication
public class WalletServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WalletServiceApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
