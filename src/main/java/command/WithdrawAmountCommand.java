package command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class WithdrawAmountCommand {

    @TargetAggregateIdentifier
    private String accountNumber;

    private Double amount;

    public WithdrawAmountCommand(String accountNumber, Double amount) {
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Double getAmount() {
        return amount;
    }
}