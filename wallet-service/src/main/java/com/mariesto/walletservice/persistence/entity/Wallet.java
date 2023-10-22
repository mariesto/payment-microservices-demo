package com.mariesto.walletservice.persistence.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "wallets")
@Getter
@Setter
public class Wallet extends BaseEntity {
    @Column (name = "user_id")
    private String userId;

    private Double balance;

    @OneToMany (mappedBy = "wallet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<WalletTransaction> transactions = new ArrayList<>();

    public void addTransaction(WalletTransaction transaction) {
        this.transactions.add(transaction);
        transaction.setWallet(this);
    }

    @Override
    public String toString() {
        return "Wallet{" + "userId='" + userId + '\'' + ", balance=" + balance + ", transactions=" + transactions + '}';
    }
}
