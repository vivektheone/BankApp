package command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class DepositAmountCommand {

    @TargetAggregateIdentifier
    private String accountNumber;

    private Double amount;

    public DepositAmountCommand(String accountNumber, Double amount) {
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
