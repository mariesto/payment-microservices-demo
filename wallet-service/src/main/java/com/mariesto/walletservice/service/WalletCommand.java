package com.mariesto.walletservice.service;

import com.mariesto.walletservice.dto.TransactionDTO;
import com.mariesto.walletservice.dto.WalletDTO;

public interface WalletCommand {
    WalletDTO execute(TransactionDTO transactionDTO);
}
