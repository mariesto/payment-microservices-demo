package com.mariesto.walletservice.persistence.repository;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.mariesto.walletservice.persistence.entity.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {

    Optional<Wallet> findWalletByUserId(final String userId);

    @Transactional
    @Modifying
    @Query("update Wallet w set w.balance = ?1 where w.userId = ?2")
    void updateWalletBalance(final Double balance, final String userId);

}
