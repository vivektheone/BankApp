package command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreateAccountCommand {

    @TargetAggregateIdentifier
    protected String accountNumber;

    protected String customerName;

    public CreateAccountCommand(String accountNumber, String customerName){
        this.accountNumber = accountNumber;
        this.customerName = customerName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }
}
