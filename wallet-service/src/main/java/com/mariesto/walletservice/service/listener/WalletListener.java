package com.mariesto.walletservice.service.listener;

import com.mariesto.walletservice.constant.TransactionType;
import com.mariesto.walletservice.service.strategy.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.mariesto.walletservice.dto.TransactionDTO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WalletListener {

    private final ModelMapper modelMapper;

    private final WalletContext walletContext;

    @RabbitListener (queues = "${rabbit-mq-props.to-pup-queue}")
    public void handleTopUp(WalletMessage walletMessage) {
        log.info("[x] consume top-up message : {}", walletMessage);
        walletContext.setWalletStrategy(TransactionType.TOP_UP);
        TransactionDTO transactionDTO = getTransactionDtoFromWalletMessage(walletMessage);
        walletContext.execute(transactionDTO);
    }

    @RabbitListener (queues = "${rabbit-mq-props.credit-queue}")
    public void handleCredit(WalletMessage walletMessage) {
        log.info("[x] consume credit message : {}", walletMessage);
        walletContext.setWalletStrategy(TransactionType.CREDIT);
        TransactionDTO transactionDTO = getTransactionDtoFromWalletMessage(walletMessage);
        walletContext.execute(transactionDTO);
    }

    @RabbitListener (queues = "${rabbit-mq-props.debit-queue}")
    public void handleDebit(WalletMessage walletMessage) {
        log.info("[x] consume debit message : {}", walletMessage);
        walletContext.setWalletStrategy(TransactionType.DEBIT);
        TransactionDTO transactionDTO = getTransactionDtoFromWalletMessage(walletMessage);
        walletContext.execute(transactionDTO);
    }

    @RabbitListener (queues = "${rabbit-mq-props.wallet-create-queue}")
    public void handleUserCreation(WalletMessage walletMessage) {
        log.info("[x] consume new user create message : {}", walletMessage);
        walletContext.setWalletStrategy(TransactionType.CREATE_WALLET);
        log.info("[STRATEGY-CONTEXT] : {}", walletContext.getWalletStrategy().toString());
        TransactionDTO transactionDTO = getTransactionDtoFromWalletMessage(walletMessage);
        walletContext.execute(transactionDTO);
    }

    private TransactionDTO getTransactionDtoFromWalletMessage(WalletMessage walletMessage) {
        return modelMapper.map(walletMessage, TransactionDTO.class);
    }
}
