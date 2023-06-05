package com.mariesto.walletservice.service.command;

import com.mariesto.walletservice.dto.TransactionDTO;

public interface WalletCommand {
    void execute(TransactionDTO transactionDTO);
}
