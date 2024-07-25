package com.mariesto.walletservice.service.strategy;

import com.mariesto.walletservice.constant.TransactionType;
import com.mariesto.walletservice.dto.TransactionDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class WalletContext {

    private final Logger logger = LoggerFactory.getLogger(WalletContext.class);

    private final TopUpStrategy topUpStrategy;

    private final DebitStrategy debitStrategy;

    private final CreditStrategy creditStrategy;

    private final CreateWalletStrategy createWalletStrategy;

    @Getter
    private WalletStrategy walletStrategy;

    public void setWalletStrategy(TransactionType type) {
        logger.info("Setting wallet strategy to " + type);
        if (type == TransactionType.DEBIT) {
            walletStrategy = debitStrategy;
            return;
        }
        if (type == TransactionType.CREDIT) {
            walletStrategy = creditStrategy;
            return;
        }
        if (type == TransactionType.TOP_UP) {
            walletStrategy = topUpStrategy;
            return;
        }
        if (type == TransactionType.CREATE_WALLET) {
            walletStrategy = createWalletStrategy;
            return;
        }
        logger.info("Unknown transaction type: {}", type);
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void execute(TransactionDTO dto) {
        walletStrategy.execute(dto);
    }
}
