package com.springBank.bankacc.core.events;

import com.springBank.bankacc.core.models.AccountType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AccountOpenedEvent {

    private String id;

    private String accountHolder;

    private AccountType accountType;

    private Date creationDate;

    private double openingBalance;
}
