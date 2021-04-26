package com.springBank.bankacc.cmd.api.commands;

import com.springBank.bankacc.core.models.AccountType;


import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class OpenAccountCommand {

    @TargetAggregateIdentifier
    private String id;

    @NotNull(message= "Account Holder Id Cannot be null")
    private String accountHolderId;

    private AccountType accountType;

    @NotNull(message= "Opening Balance Cannot be null")
    @Min(value = 50, message = "Balance should be atleast 50")
    private double openingBalance;
}
