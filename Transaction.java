import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private String transactionId;
    private String type; // DEPOSIT, WITHDRAWAL, TRANSFER
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private Account account;
    private Account relatedAccount; // For transfers

    public Transaction(String type, BigDecimal amount, Account account) {
        this.transactionId = "TXN" + System.currentTimeMillis();
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.account = account;
    }

    public Transaction(String type, BigDecimal amount, Account account, Account relatedAccount) {
        this(type, amount, account);
        this.relatedAccount = relatedAccount;
    }

    // Getters
    public String getTransactionId() { return transactionId; }
    public String getType() { return type; }
    public BigDecimal getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public Account getAccount() { return account; }
    public Account getRelatedAccount() { return relatedAccount; }
}