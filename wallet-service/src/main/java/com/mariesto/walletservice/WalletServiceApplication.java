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
import com.mariesto.walletservice.service.command.CreditCommand;
import com.mariesto.walletservice.service.command.DebitCommand;
import com.mariesto.walletservice.service.command.TopUpCommand;
import com.mariesto.walletservice.service.listener.WalletListener;

@EnableConfigurationProperties (value = RabbitMqConfigProps.class)
@EnableJpaRepositories
@ComponentScan (basePackageClasses = { WalletController.class, WalletService.class, CreditCommand.class, DebitCommand.class, TopUpCommand.class, WalletRepository.class, WalletTransactionsRepository.class, RabbitMqConfig.class, WalletListener.class })
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
