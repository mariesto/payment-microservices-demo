package com.mariesto.walletservice.service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.mariesto.walletservice.dto.TransactionDTO;
import com.mariesto.walletservice.dto.WalletDTO;
import com.mariesto.walletservice.persistence.entity.Wallet;
import com.mariesto.walletservice.persistence.entity.WalletTransaction;
import com.mariesto.walletservice.persistence.repository.WalletRepository;
import com.mariesto.walletservice.persistence.repository.WalletTransactionsRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;

    private final WalletTransactionsRepository walletTransactionsRepository;

    private final ModelMapper modelMapper;

    public WalletDTO fetchUserWallet(String userId) {
        log.info("fetching wallet for : {}", userId);
        Wallet fetchedWallet = walletRepository.findWalletByUserId(userId);
        log.info("result fetching wallet : {}", fetchedWallet.toString());
        return modelMapper.map(fetchedWallet, WalletDTO.class);
    }

    public List<TransactionDTO> getUserTransactions(String userId) {
        List<WalletTransaction> walletTransactions = walletTransactionsRepository.findAllByWalletUserId(userId);
        return walletTransactions.stream().map(walletTransaction -> modelMapper.map(walletTransactions, TransactionDTO.class))
                                 .collect(Collectors.toList());
    }

}
