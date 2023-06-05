package com.mariesto.walletservice.service.command;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.mariesto.walletservice.constant.TransactionType;
import com.mariesto.walletservice.dto.TransactionDTO;
import com.mariesto.walletservice.dto.WalletDTO;
import com.mariesto.walletservice.persistence.entity.Wallet;
import com.mariesto.walletservice.persistence.entity.WalletTransaction;
import com.mariesto.walletservice.persistence.repository.WalletRepository;
import com.mariesto.walletservice.persistence.repository.WalletTransactionsRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CreditCommand implements WalletCommand {

    private final Logger LOG = LoggerFactory.getLogger(CreditCommand.class);

    private final WalletRepository walletRepository;

    private final WalletTransactionsRepository walletTransactionsRepository;

    private final ModelMapper modelMapper;

    public CreditCommand(WalletRepository walletRepository, WalletTransactionsRepository walletTransactionsRepository, ModelMapper modelMapper) {
        this.walletRepository = walletRepository;
        this.walletTransactionsRepository = walletTransactionsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void execute(TransactionDTO transactionDTO) {
        final Optional<Wallet> wallet = walletRepository.findWalletByUserId(transactionDTO.getUserId());
        if (wallet.isEmpty()){
            LOG.error("wallet not found for user id : {}", transactionDTO.getUserId());
        }
        WalletTransaction walletTransaction = modelMapper.map(transactionDTO, WalletTransaction.class);
        walletTransaction.setTransactionType(TransactionType.CREDIT);
        walletTransactionsRepository.save(walletTransaction);

        final Wallet fetchedWallet = wallet.get();
        Double finalBalance = fetchedWallet.getBalance() - transactionDTO.getAmount();
        walletRepository.updateWalletBalance(finalBalance, transactionDTO.getUserId());
        LOG.info("final user wallet balance : {}", fetchedWallet.getBalance());

        modelMapper.map(fetchedWallet, WalletDTO.class);
    }
}
