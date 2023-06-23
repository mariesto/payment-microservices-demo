package com.mariesto.walletservice.service.command;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.mariesto.walletservice.dto.TransactionDTO;
import com.mariesto.walletservice.persistence.entity.Wallet;
import com.mariesto.walletservice.persistence.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WalletCreateCommand implements WalletCommand {
    private final Logger logger = LoggerFactory.getLogger(WalletCreateCommand.class);

    private final WalletRepository walletRepository;

    public WalletCreateCommand(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public void execute(TransactionDTO transactionDTO) {
        Optional<Wallet> fetchedWallet = walletRepository.findWalletByUserId(transactionDTO.getUserId());
        if (fetchedWallet.isPresent()) {
            logger.error("wallet already exist for user_id : {}", transactionDTO.getUserId());
            return;
        }

        Wallet wallet = new Wallet();
        wallet.setUserId(transactionDTO.getUserId());
        wallet.setBalance(transactionDTO.getAmount());
        walletRepository.save(wallet);
    }
}
