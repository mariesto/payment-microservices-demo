package com.mariesto.walletservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping ("top-up")
    public ResponseEntity<WalletDTO> topUpBalance(@RequestBody TransactionDTO dto) {
        WalletDTO walletDTO = walletService.topUp(dto);
        return ResponseEntity.ok().body(walletDTO);
    }

    @PostMapping ("/credit")
    public ResponseEntity<WalletDTO> creditTransaction(@RequestBody TransactionDTO dto) {
        WalletDTO walletDTO = walletService.performCreditTransaction(dto);
        return ResponseEntity.ok().body(walletDTO);
    }

    @PostMapping ("/debit")
    public ResponseEntity<WalletDTO> debitTransaction(@RequestBody TransactionDTO dto) {
        WalletDTO walletDTO = walletService.performDebitTransaction(dto);
        return ResponseEntity.ok().body(walletDTO);
    }
}
