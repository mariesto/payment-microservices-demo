package com.mariesto.walletservice.persistence.entity;

import javax.persistence.Column;
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
    @Column
    @Enumerated (EnumType.STRING)
    private TransactionType transactionType;

    @Column
    private String customerPaymentId;

    @Column
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

}
