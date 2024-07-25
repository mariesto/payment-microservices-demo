package com.mariesto.walletservice.service.strategy;

import com.mariesto.walletservice.dto.TransactionDTO;
import com.mariesto.walletservice.persistence.entity.Wallet;
import com.mariesto.walletservice.persistence.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateWalletStrategy implements WalletStrategy {

    private final WalletRepository walletRepository;

    @Transactional
    @Override
    public void execute(TransactionDTO transactionDTO) {
        Optional<Wallet> fetchedWallet = Optional.ofNullable(walletRepository.findWalletByUserId(transactionDTO.getUserId()));
        if (fetchedWallet.isPresent()) {
            log.error("wallet already exist for user_id : {}", transactionDTO.getUserId());
            return;
        }
        log.info("Creating wallet for user_id : {}", transactionDTO.getUserId());
        Wallet wallet = new Wallet();
        wallet.setUserId(transactionDTO.getUserId());
        wallet.setBalance(transactionDTO.getAmount());
    }
}
