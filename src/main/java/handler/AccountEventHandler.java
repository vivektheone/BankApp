package handler;

import event.AccountCreatedEvent;
import event.AmountDepositEvent;
import event.AmountWithdrawEvent;
import model.Account;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import query.AccountView;


public class AccountEventHandler {

    private final Logger LOG = LoggerFactory.getLogger(AccountEventHandler.class);

    @EventHandler
    public void handle(AccountCreatedEvent event) {
        AccountView.accounts.put(event.getAccountNumber(), new Account(event.getAccountNumber(), event.getCustomerName()));
        LOG.info("aggregate created {}, customer {} ", event.getAccountNumber(), event.getCustomerName());
    }


    @EventHandler
    public void handle(AmountDepositEvent event) {
        Account account = AccountView.accounts.get(event.getAccountNumber());
        account.setBalance(account.getBalance() + event.getAmount());
        AccountView.accounts.put(event.getAccountNumber(), account);
        LOG.info("aggregate {}, deposit {} ", event.getAccountNumber(), event.getAmount());
    }

    @EventHandler
    public void handle(AmountWithdrawEvent event) {
        Account account = AccountView.accounts.get(event.getAccountNumber());
        account.setBalance(account.getBalance() + event.getAmount());
        LOG.info("aggregate {}, withdrawal {} ", event.getAccountNumber(), event.getAmount());
    }
}