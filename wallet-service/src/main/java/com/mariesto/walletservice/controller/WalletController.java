package com.mariesto.walletservice.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mariesto.walletservice.dto.TransactionDTO;
import com.mariesto.walletservice.dto.WalletDTO;
import com.mariesto.walletservice.service.WalletService;

@RestController
@RequestMapping ("/api/v1/wallets")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping ("/transactions/{user_id}")
    public ResponseEntity<List<TransactionDTO>> fetchUserTransaction(@PathVariable ("user_id") String userId) {
        List<TransactionDTO> userTransactions = walletService.getUserTransactions(userId);
        return ResponseEntity.ok().body(userTransactions);
    }

    @GetMapping ("/{user_id}")
    public ResponseEntity<WalletDTO> fetchUserWallet(@PathVariable ("user_id") String userId) {
        WalletDTO fetchUserWallet = walletService.fetchUserWallet(userId);
        return ResponseEntity.ok().body(fetchUserWallet);
    }

}
