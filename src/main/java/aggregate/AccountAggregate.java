package aggregate;

import command.CreateAccountCommand;
import command.DepositAmountCommand;
import command.TransferCommand;
import command.WithdrawAmountCommand;
import event.AccountCreatedEvent;
import event.AmountDepositEvent;
import event.AmountTransferEvent;
import event.AmountWithdrawEvent;
import model.Account;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import query.AccountView;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

public class AccountAggregate {

    @AggregateIdentifier
    private String accountNumber;

    public AccountAggregate() {}

    @CommandHandler
    public AccountAggregate(CreateAccountCommand command){
        apply(new AccountCreatedEvent(command.getAccountNumber(),command.getCustomerName()));
    }

    @EventHandler
    public void on(AccountCreatedEvent event){
        this.accountNumber = event.getAccountNumber();
    }


    @CommandHandler
    public void deposit(DepositAmountCommand command) {
        apply(new AmountDepositEvent(command.getAccountNumber(), command.getAmount()));
    }

    @CommandHandler
    public void withdrawal(WithdrawAmountCommand command) {
        apply(new AmountWithdrawEvent(command.getAccountNumber(), command.getAmount()));
    }


    @CommandHandler
    public void transfer(TransferCommand command) {
        apply(new AmountTransferEvent(command.getAccountNumber(), command.getToAccountNumber(), command.getAmount()));
    }

}
