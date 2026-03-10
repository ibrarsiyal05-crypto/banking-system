import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String bankId;
    private String name;
    private List<Customer> customers;

    public Bank(String bankId, String name) {
        this.bankId = bankId;
        this.name = name;
        this.customers = new ArrayList<>();
    }

    // Getters
    public String getBankId() { return bankId; }
    public String getName() { return name; }
    public List<Customer> getCustomers() { return customers; }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer getCustomer(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    public Account getAccount(String accountNumber) {
        for (Customer customer : customers) {
            Account account = customer.getAccount(accountNumber);
            if (account != null) {
                return account;
            }
        }
        return null;
    }
}
