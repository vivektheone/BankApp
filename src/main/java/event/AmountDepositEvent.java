package event;

public class AmountDepositEvent {
    private String accountNumber;

    private Double amount;

    public AmountDepositEvent(String accountNumber, Double amount){
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
