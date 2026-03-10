import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountNumber;
    private String accountType; // SAVINGS, CURRENT, etc.
    private BigDecimal balance;
    private Customer customer;
    private List<Transaction> transactions;

    public Account(String accountNumber, String accountType, Customer customer) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.customer = customer;
        this.balance = BigDecimal.ZERO;
        this.transactions = new ArrayList<>();
    }

    // Getters and setters
    public String getAccountNumber() { return accountNumber; }
    public String getAccountType() { return accountType; }
    public BigDecimal getBalance() { return balance; }
    public Customer getCustomer() { return customer; }
    public List<Transaction> getTransactions() { return transactions; }

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
        transactions.add(new Transaction("DEPOSIT", amount, this));
    }

    public void withdraw(BigDecimal amount) throws InsufficientBalanceException {
        if (balance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance in account " + accountNumber);
        }
        balance = balance.subtract(amount);
        transactions.add(new Transaction("WITHDRAWAL", amount.negate(), this));
    }

    public void transfer(Account recipient, BigDecimal amount) 
            throws InsufficientBalanceException {
        this.withdraw(amount);
        recipient.deposit(amount);
        transactions.add(new Transaction("TRANSFER_OUT", amount.negate(), this, recipient));
        recipient.getTransactions().add(new Transaction("TRANSFER_IN", amount, recipient, this));
    }
}