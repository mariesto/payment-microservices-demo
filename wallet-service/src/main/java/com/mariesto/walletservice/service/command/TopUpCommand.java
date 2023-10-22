package com.mariesto.walletservice.service.command;

import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.mariesto.walletservice.constant.TransactionType;
import com.mariesto.walletservice.dto.TransactionDTO;
import com.mariesto.walletservice.persistence.entity.Wallet;
import com.mariesto.walletservice.persistence.entity.WalletTransaction;
import com.mariesto.walletservice.persistence.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TopUpCommand implements WalletCommand {
    private final Logger logger = LoggerFactory.getLogger(TopUpCommand.class);

    private final WalletRepository walletRepository;

    public TopUpCommand(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Transactional
    @Override
    public void execute(TransactionDTO transactionDTO) {
        Optional<Wallet> wallet = Optional.ofNullable(walletRepository.findWalletByUserId(transactionDTO.getUserId()));
        if (wallet.isEmpty()) {
            logger.error("wallet not found for user id : {}", transactionDTO.getUserId());
            return;
        }
        Wallet fetchedWallet = wallet.get();
        Double finalBalance = fetchedWallet.getBalance() + transactionDTO.getAmount();
        fetchedWallet.setBalance(finalBalance);

        WalletTransaction transaction = new WalletTransaction();
        transaction.setTransactionType(TransactionType.TOP_UP);
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setPaymentReferenceId(UUID.randomUUID().toString());
        fetchedWallet.addTransaction(transaction);
    }
}
