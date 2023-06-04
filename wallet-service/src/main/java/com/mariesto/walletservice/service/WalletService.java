package com.mariesto.walletservice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mariesto.walletservice.dto.TransactionDTO;
import com.mariesto.walletservice.dto.WalletDTO;
import com.mariesto.walletservice.service.command.CreditCommand;
import com.mariesto.walletservice.service.command.DebitCommand;
import com.mariesto.walletservice.service.command.TopUpCommand;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WalletService {
    private final TopUpCommand topUpCommand;

    private final CreditCommand creditCommand;

    private final DebitCommand debitCommand;

    public WalletService(TopUpCommand topUpCommand, CreditCommand creditCommand, DebitCommand debitCommand) {
        this.topUpCommand = topUpCommand;
        this.creditCommand = creditCommand;
        this.debitCommand = debitCommand;
    }

    @Transactional
    public WalletDTO topUp(TransactionDTO dto) {
        return topUpCommand.execute(dto);
    }

    @Transactional
    public WalletDTO performCreditTransaction(TransactionDTO dto) {
        return creditCommand.execute(dto);
    }

    @Transactional
    public WalletDTO performDebitTransaction(TransactionDTO dto) {
        return debitCommand.execute(dto);
    }
}
