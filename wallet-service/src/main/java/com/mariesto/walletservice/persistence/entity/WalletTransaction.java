package com.mariesto.walletservice.persistence.entity;

import com.mariesto.walletservice.constant.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "wallet_transactions")
@Getter
@Setter
public class WalletTransaction extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private String paymentReferenceId;

    private Double amount;

    @ManyToOne
    @JoinColumn (name = "wallet_id")
    private Wallet wallet;

}
