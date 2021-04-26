package com.springBank.bankacc.query.api.handlers;

import com.springBank.bankacc.core.events.AccountClosedEvent;
import com.springBank.bankacc.core.events.AccountOpenedEvent;
import com.springBank.bankacc.core.events.FundsDepositedEvent;
import com.springBank.bankacc.core.events.FundsWithdrawnEvent;
import com.springBank.bankacc.core.models.BankAccount;
import com.springBank.bankacc.query.api.repositories.AcountRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@ProcessingGroup("bankaccount-group")
@Service
public class AccountEventHandlerImpl implements AccountEventHandler {

    private final AcountRepository acountRepository;

    @Autowired
    public AccountEventHandlerImpl(AcountRepository acountRepository) {
        this.acountRepository = acountRepository;
    }


    @Override
    @EventHandler
    public void on(AccountOpenedEvent event) {
        var bankAccount = BankAccount.builder()
                .id(event.getId())
                .accountHolderId(event.getAccountHolder())
                .accountType(event.getAccountType())
                .creationDate(event.getCreationDate())
                .balance(event.getOpeningBalance())
                .build();

        acountRepository.save(bankAccount);
    }

    @Override
    @EventHandler
    public void on(FundsDepositedEvent event) {
        var bankAccountExists = acountRepository.findById(event.getId());
        if(bankAccountExists.isEmpty()) return ;
        var bankAccount = bankAccountExists.get();
        bankAccount.setBalance(event.getBalance());
        acountRepository.save(bankAccount);
    }

    @Override
    @EventHandler
    public void on(FundsWithdrawnEvent event) {
        var bankAccountExists = acountRepository.findById(event.getId());
        if(bankAccountExists.isEmpty()) return ;
        var bankAccount = bankAccountExists.get();
        bankAccount.setBalance(event.getBalance());
        acountRepository.save(bankAccount);
    }

    @Override
    @EventHandler
    public void on(AccountClosedEvent event) {
        var bankAccountExists = acountRepository.findById(event.getId());
        if(bankAccountExists.isEmpty()) return ;
        acountRepository.deleteById(event.getId());
    }
}
