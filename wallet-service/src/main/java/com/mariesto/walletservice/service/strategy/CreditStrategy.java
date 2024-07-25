package com.mariesto.walletservice.service.strategy;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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

@Component
@Slf4j
@RequiredArgsConstructor
public class CreditStrategy implements WalletStrategy {
    private final Logger logger = LoggerFactory.getLogger(CreditStrategy.class);

    private final WalletRepository walletRepository;

    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public void execute(TransactionDTO transactionDTO) {
        final Optional<Wallet> wallet = Optional.ofNullable(walletRepository.findWalletByUserId(transactionDTO.getUserId()));
        if (wallet.isEmpty()) {
            logger.error("wallet not found for user id : {}", transactionDTO.getUserId());
            return;
        }

        final Wallet fetchedWallet = wallet.get();
        Double finalBalance = fetchedWallet.getBalance() - transactionDTO.getAmount();
        fetchedWallet.setBalance(finalBalance);

        WalletTransaction walletTransaction = modelMapper.map(transactionDTO, WalletTransaction.class);
        walletTransaction.setTransactionType(TransactionType.CREDIT);
        fetchedWallet.addTransaction(walletTransaction);
    }
}
