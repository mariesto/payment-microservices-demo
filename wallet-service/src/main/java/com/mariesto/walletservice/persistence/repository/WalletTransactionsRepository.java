package com.mariesto.walletservice.persistence.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mariesto.walletservice.persistence.entity.WalletTransaction;

@Repository
public interface WalletTransactionsRepository extends JpaRepository<WalletTransaction, Long> {
    List<WalletTransaction> findAllByWalletUserId(String userId);

}
