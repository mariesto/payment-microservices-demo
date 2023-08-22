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
    @Column
    private String userId;


    @Column
    private Double balance;

    @OneToMany (mappedBy = "wallet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<WalletTransaction> transactions = new ArrayList<>();
}
