package com.mariesto.walletservice.service.listener;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.mariesto.walletservice.dto.TransactionDTO;
import com.mariesto.walletservice.service.command.CreditCommand;
import com.mariesto.walletservice.service.command.DebitCommand;
import com.mariesto.walletservice.service.command.TopUpCommand;
import com.mariesto.walletservice.service.command.WalletCreateCommand;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WalletListener {
    private final TopUpCommand topUpCommand;

    private final DebitCommand debitCommand;

    private final CreditCommand creditCommand;

    private final WalletCreateCommand walletCreateCommand;

    private final ModelMapper modelMapper;

    public WalletListener(TopUpCommand topUpCommand, DebitCommand debitCommand, CreditCommand creditCommand, WalletCreateCommand walletCreateCommand,
            ModelMapper modelMapper) {
        this.topUpCommand = topUpCommand;
        this.debitCommand = debitCommand;
        this.creditCommand = creditCommand;
        this.walletCreateCommand = walletCreateCommand;
        this.modelMapper = modelMapper;
    }

    @RabbitListener (queues = "${rabbit-mq-props.to-pup-queue}")
    public void handleTopUp(WalletMessage walletMessage) {
        log.info("[x] consume top-up message : {}", walletMessage);
        TransactionDTO transactionDTO = getTransactionDtoFromWalletMessage(walletMessage);
        topUpCommand.execute(transactionDTO);
    }

    @RabbitListener (queues = "${rabbit-mq-props.credit-queue}")
    public void handleCredit(WalletMessage walletMessage) {
        log.info("[x] consume credit message : {}", walletMessage);
        TransactionDTO transactionDTO = getTransactionDtoFromWalletMessage(walletMessage);
        creditCommand.execute(transactionDTO);
    }

    @RabbitListener (queues = "${rabbit-mq-props.debit-queue}")
    public void handleDebit(WalletMessage walletMessage) {
        log.info("[x] consume debit message : {}", walletMessage);
        TransactionDTO transactionDTO = getTransactionDtoFromWalletMessage(walletMessage);
        debitCommand.execute(transactionDTO);
    }

    @RabbitListener (queues = "${rabbit-mq-props.wallet-create-queue}")
    public void handleUserCreation(WalletMessage walletMessage) {
        log.info("[x] consume new user create message : {}", walletMessage);
        TransactionDTO transactionDTO = getTransactionDtoFromWalletMessage(walletMessage);
        walletCreateCommand.execute(transactionDTO);
    }

    private TransactionDTO getTransactionDtoFromWalletMessage(WalletMessage walletMessage) {
        return modelMapper.map(walletMessage, TransactionDTO.class);
    }
}
