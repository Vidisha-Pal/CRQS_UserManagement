package com.springBank.bankacc.cmd.api.aggregates;

import com.springBank.bankacc.cmd.api.commands.CloseAccountCommand;
import com.springBank.bankacc.cmd.api.commands.DepositFundsCommand;
import com.springBank.bankacc.cmd.api.commands.OpenAccountCommand;
import com.springBank.bankacc.cmd.api.commands.WithdrawFundsCommand;
import com.springBank.bankacc.core.events.AccountClosedEvent;
import com.springBank.bankacc.core.events.AccountOpenedEvent;
import com.springBank.bankacc.core.events.FundsDepositedEvent;
import com.springBank.bankacc.core.events.FundsWithdrawnEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateAnnotationCommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Date;
import java.util.UUID;

@Aggregate
@NoArgsConstructor
public class AccountAggregate {

    @AggregateIdentifier
    private String id;

    private String accountHolderId;

    private double balance;

    @CommandHandler
    public AccountAggregate(OpenAccountCommand command) {

        //generate a event from the command
        var event = AccountOpenedEvent.builder()
                .id(command.getId())
                .accountHolder(command.getAccountHolderId())
                .accountType(command.getAccountType())
                .openingBalance(command.getOpeningBalance())
                .creationDate(new Date())
                .build();

        // publish the event to eventbus
        AggregateLifecycle.apply(event);
    }

    // method called when the aggregate is sourced from an event
    // resposible for changing the state of the aggregate
    @EventSourcingHandler
    public void on(AccountOpenedEvent event){
       this.id = event.getId();
       this.accountHolderId = event.getAccountHolder();
       this.balance  = event.getOpeningBalance();
    }


    @CommandHandler
    public void handle (DepositFundsCommand command) {
        var event =
        FundsDepositedEvent.builder()
                .id(command.getId())
                .amount(command.getAmount())
                .balance(this.balance + (command.getAmount()))
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on (FundsDepositedEvent event){
        this.balance = event.getAmount();
    }


    @CommandHandler
    public void on(WithdrawFundsCommand command) {
        if(this.balance - command.getAmount() < 0) throw new IllegalStateException("Withdrawl declined. Insufficient funds.");
        var event = FundsWithdrawnEvent.builder()
                .id(command.getId())
                .amount(command.getAmount())
                .balance(this.balance - command.getAmount())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on (FundsWithdrawnEvent event) {
        this.balance = event.getBalance();
    }

    @CommandHandler
    public void handle(CloseAccountCommand command) {
        var event = AccountClosedEvent.builder()
                .id(command.getId())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on (AccountClosedEvent event) {
       AggregateLifecycle.markDeleted();
    }
}



