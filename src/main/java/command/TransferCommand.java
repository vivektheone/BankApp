package command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class TransferCommand {

    @TargetAggregateIdentifier
    private String accountNumber;

    private Double amount;

    private String toAccountNumber;

    public TransferCommand(String fromAccountNumber, String toAccountNumber, Double amount) {
        this.accountNumber = fromAccountNumber;
        this.amount = amount;
        this.toAccountNumber = toAccountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }
}
