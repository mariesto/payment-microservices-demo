package com.mariesto.walletservice.service;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.mariesto.walletservice.dto.TransactionDTO;
import com.mariesto.walletservice.persistence.entity.WalletTransaction;
import com.mariesto.walletservice.persistence.repository.WalletTransactionsRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WalletService {
    private final WalletTransactionsRepository walletTransactionsRepository;

    private final ModelMapper modelMapper;

    public WalletService(WalletTransactionsRepository walletTransactionsRepository, ModelMapper modelMapper) {
        this.walletTransactionsRepository = walletTransactionsRepository;
        this.modelMapper = modelMapper;
    }

    public List<TransactionDTO> getUserTransactions(String userId) {
        List<WalletTransaction> walletTransactions = walletTransactionsRepository.findAllByWalletUserId(userId);
        return walletTransactions.stream().map(walletTransaction -> modelMapper.map(walletTransactions, TransactionDTO.class))
                                 .collect(Collectors.toList());
    }
}
