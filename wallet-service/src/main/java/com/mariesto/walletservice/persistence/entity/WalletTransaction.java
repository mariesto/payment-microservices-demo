package com.mariesto.walletservice.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.mariesto.walletservice.constant.TransactionType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "wallet_transactions")
@Getter
@Setter
public class WalletTransaction extends BaseEntity {
    @Enumerated (EnumType.STRING)
    private TransactionType transactionType;

    private String paymentReferenceId;

    private Double amount;

    @ManyToOne
    @JoinColumn (name = "wallet_id")
    private Wallet wallet;

}
