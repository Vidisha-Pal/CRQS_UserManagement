package com.springBank.bankacc.query.api.handlers;

import com.springBank.bankacc.core.events.AccountClosedEvent;
import com.springBank.bankacc.core.events.AccountOpenedEvent;
import com.springBank.bankacc.core.events.FundsDepositedEvent;
import com.springBank.bankacc.core.events.FundsWithdrawnEvent;

public interface AccountEventHandler {

    public void on (AccountOpenedEvent event);

    public void on(FundsDepositedEvent event);

    public void on(FundsWithdrawnEvent event);

    public void on (AccountClosedEvent event);
}
