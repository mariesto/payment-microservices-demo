package com.mariesto.walletservice.service.listener;

import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class WalletMessage implements Serializable {
    @NotNull
    @Valid
    private String userId;

    @NotNull
    @Valid
    private Double amount;

}
