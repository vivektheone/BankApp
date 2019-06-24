package event;

public class AmountWithdrawEvent {
    private String accountNumber;

    private Double amount;

    public AmountWithdrawEvent(String accountNumber, Double amount){
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
