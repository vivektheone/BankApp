package handler;

import event.AccountCreatedEvent;
import event.AmountDepositEvent;
import event.AmountTransferEvent;
import event.AmountWithdrawEvent;
import model.Account;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import query.AccountView;

import java.util.Timer;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;


public class AccountEventHandler {

    private final Logger LOG = LoggerFactory.getLogger(AccountEventHandler.class);
    private static int counter = 0;

    //Todo: AccountExistEvent to prevent duplicates...
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

    @EventHandler
    public void handle(AmountTransferEvent event){
        long start = System.nanoTime();
        Account fromAccount = AccountView.accounts.get(event.getFromAccount());
        Account toAccount = AccountView.accounts.get(event.getToAccount());
        CompletableFuture.supplyAsync(() -> {
            fromAccount.setBalance(fromAccount.getBalance() - event.getTransferAmount());
            toAccount.setBalance(toAccount.getBalance() + event.getTransferAmount());
            return true;
        }).thenRun(() -> {
            AccountView.accounts.put(event.getFromAccount(), fromAccount);
            AccountView.accounts.put(event.getToAccount(), toAccount);
            LOG.info("From {}, To {}, Wired {}, Count {}", event.getFromAccount(), event.getToAccount(), event.getTransferAmount());
            LOG.info("Time taken {} nanosec", System.nanoTime() - start);
        });
    }
}