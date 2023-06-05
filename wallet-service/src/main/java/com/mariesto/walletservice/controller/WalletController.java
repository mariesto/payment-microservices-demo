package com.mariesto.walletservice.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mariesto.walletservice.dto.TransactionDTO;
import com.mariesto.walletservice.service.WalletService;

@RestController
@RequestMapping ("/api/v1/wallets")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping ("/transactions/{userId}")
    public ResponseEntity<List<TransactionDTO>> fetchUserTransaction(@PathVariable ("userId") String userId) {
        List<TransactionDTO> userTransactions = walletService.getUserTransactions(userId);
        return ResponseEntity.ok().body(userTransactions);
    }
}
