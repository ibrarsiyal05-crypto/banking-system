import java.math.BigDecimal;
import java.util.Scanner;

public class BankingSystem {
    private Bank bank;
    private Scanner scanner;

    public BankingSystem() {
        this.bank = new Bank("BANK001", "Java Banking System");
        this.scanner = new Scanner(System.in);
        initializeSampleData();
    }

    private void initializeSampleData() {
        // Create sample customers and accounts
        Customer customer1 = new Customer("CUST001", "John Doe", "john@example.com", "1234567890");
        Customer customer2 = new Customer("CUST002", "Jane Smith", "jane@example.com", "9876543210");
        
        Account account1 = new Account("ACC1001", "SAVINGS", customer1);
        Account account2 = new Account("ACC1002", "CURRENT", customer1);
        Account account3 = new Account("ACC2001", "SAVINGS", customer2);
        
        customer1.addAccount(account1);
        customer1.addAccount(account2);
        customer2.addAccount(account3);
        
        // Initial deposits
        account1.deposit(new BigDecimal("1000.00"));
        account2.deposit(new BigDecimal("5000.00"));
        account3.deposit(new BigDecimal("2500.00"));
        
        bank.addCustomer(customer1);
        bank.addCustomer(customer2);
    }

    public void start() {
        System.out.println("Welcome to " + bank.getName());
        
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Customer Operations");
            System.out.println("2. Account Operations");
            System.out.println("3. Transaction Operations");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    customerOperations();
                    break;
                case 2:
                    accountOperations();
                    break;
                case 3:
                    transactionOperations();
                    break;
                case 4:
                    System.out.println("Thank you for using our banking system!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void customerOperations() {
        System.out.println("\nCustomer Operations:");
        System.out.println("1. Add New Customer");
        System.out.println("2. View Customer Details");
        System.out.println("3. List All Customers");
        System.out.println("4. Back to Main Menu");
        System.out.print("Enter your choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice) {
            case 1:
                addNewCustomer();
                break;
            case 2:
                viewCustomerDetails();
                break;
            case 3:
                listAllCustomers();
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void addNewCustomer() {
        System.out.println("\nAdd New Customer:");
        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        
        Customer customer = new Customer(customerId, name, email, phone);
        bank.addCustomer(customer);
        System.out.println("Customer added successfully!");
    }

    private void viewCustomerDetails() {
        System.out.print("\nEnter Customer ID: ");
        String customerId = scanner.nextLine();
        
        Customer customer = bank.getCustomer(customerId);
        if (customer != null) {
            System.out.println("\nCustomer Details:");
            System.out.println("ID: " + customer.getCustomerId());
            System.out.println("Name: " + customer.getName());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("Phone: " + customer.getPhone());
            
            System.out.println("\nAccounts:");
            for (Account account : customer.getAccounts()) {
                System.out.println(account.getAccountNumber() + " - " + 
                    account.getAccountType() + " - Balance: $" + account.getBalance());
            }
        } else {
            System.out.println("Customer not found.");
        }
    }

    private void listAllCustomers() {
        System.out.println("\nAll Customers:");
        for (Customer customer : bank.getCustomers()) {
            System.out.println(customer.getCustomerId() + " - " + customer.getName());
        }
    }

    private void accountOperations() {
        System.out.println("\nAccount Operations:");
        System.out.println("1. Create New Account");
        System.out.println("2. View Account Balance");
        System.out.println("3. View Transaction History");
        System.out.println("4. Back to Main Menu");
        System.out.print("Enter your choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice) {
            case 1:
                createNewAccount();
                break;
            case 2:
                viewAccountBalance();
                break;
            case 3:
                viewTransactionHistory();
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void createNewAccount() {
        System.out.println("\nCreate New Account:");
        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine();
        
        Customer customer = bank.getCustomer(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }
        
        System.out.print("Enter Account Type (SAVINGS/CURRENT): ");
        String accountType = scanner.nextLine();
        
        String accountNumber = "ACC" + (1000 + customer.getAccounts().size() + 1);
        Account account = new Account(accountNumber, accountType, customer);
        customer.addAccount(account);
        
        System.out.println("Account created successfully!");
        System.out.println("Account Number: " + accountNumber);
    }

    private void viewAccountBalance() {
        System.out.print("\nEnter Account Number: ");
        String accountNumber = scanner.nextLine();
        
        Account account = bank.getAccount(accountNumber);
        if (account != null) {
            System.out.println("\nAccount Balance:");
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Type: " + account.getAccountType());
            System.out.println("Balance: $" + account.getBalance());
            System.out.println("Customer: " + account.getCustomer().getName());
        } else {
            System.out.println("Account not found.");
        }
    }

    private void viewTransactionHistory() {
        System.out.print("\nEnter Account Number: ");
        String accountNumber = scanner.nextLine();
        
        Account account = bank.getAccount(accountNumber);
        if (account != null) {
            System.out.println("\nTransaction History for Account: " + accountNumber);
            System.out.println("Customer: " + account.getCustomer().getName());
            
            System.out.println("\nDate\t\tType\t\tAmount\t\tRelated Account");
            for (Transaction txn : account.getTransactions()) {
                System.out.print(txn.getTimestamp().toLocalDate() + "\t");
                System.out.print(txn.getType() + "\t");
                System.out.print("$" + txn.getAmount() + "\t");
                if (txn.getRelatedAccount() != null) {
                    System.out.print(txn.getRelatedAccount().getAccountNumber());
                }
                System.out.println();
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private void transactionOperations() {
        System.out.println("\nTransaction Operations:");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Transfer");
        System.out.println("4. Back to Main Menu");
        System.out.print("Enter your choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice) {
            case 1:
                deposit();
                break;
            case 2:
                withdraw();
                break;
            case 3:
                transfer();
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void deposit() {
        System.out.println("\nDeposit Money:");
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        
        Account account = bank.getAccount(accountNumber);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
        
        System.out.print("Enter Amount to Deposit: ");
        BigDecimal amount = scanner.nextBigDecimal();
        scanner.nextLine();
        
        account.deposit(amount);
        System.out.println("Deposit successful. New balance: $" + account.getBalance());
    }

    private void withdraw() {
        System.out.println("\nWithdraw Money:");
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        
        Account account = bank.getAccount(accountNumber);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
        
        System.out.print("Enter Amount to Withdraw: ");
        BigDecimal amount = scanner.nextBigDecimal();
        scanner.nextLine();
        
        try {
            account.withdraw(amount);
            System.out.println("Withdrawal successful. New balance: $" + account.getBalance());
        } catch (InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void transfer() {
        System.out.println("\nTransfer Money:");
        System.out.print("Enter Source Account Number: ");
        String fromAccount = scanner.nextLine();
        
        System.out.print("Enter Destination Account Number: ");
        String toAccount = scanner.nextLine();
        
        Account source = bank.getAccount(fromAccount);
        Account destination = bank.getAccount(toAccount);
        
        if (source == null || destination == null) {
            System.out.println("One or both accounts not found.");
            return;
        }
        
        System.out.print("Enter Amount to Transfer: ");
        BigDecimal amount = scanner.nextBigDecimal();
        scanner.nextLine();
        
        try {
            source.transfer(destination, amount);
            System.out.println("Transfer successful.");
            System.out.println("Source account new balance: $" + source.getBalance());
            System.out.println("Destination account new balance: $" + destination.getBalance());
        } catch (InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        BankingSystem bankingSystem = new BankingSystem();
        bankingSystem.start();
    }
}