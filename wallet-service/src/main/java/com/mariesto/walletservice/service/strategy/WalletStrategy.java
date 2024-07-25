package com.mariesto.walletservice.service.strategy;

import com.mariesto.walletservice.dto.TransactionDTO;

public interface WalletStrategy {

    void execute(TransactionDTO transactionDTO);
}
