package event;

public class AmountTransferEvent {

    private String fromAccount;

    private String toAccount;

    private Double transferAmount;

    public AmountTransferEvent(String from, String to, Double amount){
        this.fromAccount = from;
        this.toAccount = to;
        this.transferAmount = amount;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public Double getTransferAmount() {
        return transferAmount;
    }
}
