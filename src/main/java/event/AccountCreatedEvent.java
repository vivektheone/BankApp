package event;

public class AccountCreatedEvent {

    private String accountNumber;

    private String customerName;

    public AccountCreatedEvent(String accountNumber, String customerName) {
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
