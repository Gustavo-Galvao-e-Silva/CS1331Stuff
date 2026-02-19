import java.util.Date;

public class Transaction {
    private int transactionId;
    private int originId;
    private int destinationId;
    private double amount;
    private Date date;

    public int getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(int newTransactionId) {
        this.transactionId = newTransactionId;
    }

}
