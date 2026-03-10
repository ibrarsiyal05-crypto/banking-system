# Java Banking System

A console-based banking application built in Java that simulates core banking operations including customer management, account handling, and financial transactions.

## Features

- **Customer Management** – Add new customers, view customer details, and list all customers
- **Account Management** – Create savings/current accounts, view balances, and browse transaction history
- **Transactions** – Deposit, withdraw, and transfer funds between accounts
- **Exception Handling** – Custom exceptions for insufficient balance and account not found scenarios

## Project Structure

```
├── BankingSystem.java          # Entry point; console UI and menu navigation
├── Bank.java                   # Top-level bank entity; manages customers
├── Customer.java               # Customer entity; holds accounts
├── Account.java                # Account entity; handles deposits, withdrawals, transfers
├── Transaction.java            # Transaction record with timestamp and type
├── InsufficientBalanceException.java   # Thrown when withdrawal/transfer exceeds balance
└── AccountNotFoundException.java       # Thrown when an account lookup fails
```

## Class Overview

### `BankingSystem`
The main class and application entry point. Manages the interactive CLI menu and delegates operations to the `Bank`, `Customer`, and `Account` classes.

### `Bank`
Holds a list of `Customer` objects and provides lookup methods for both customers and accounts across the entire bank.

### `Customer`
Represents a bank customer with an ID, name, email, and phone number. Maintains a list of associated `Account` objects.

### `Account`
Core financial entity. Stores account number, type (`SAVINGS` or `CURRENT`), balance, and transaction history. Exposes `deposit()`, `withdraw()`, and `transfer()` methods.

### `Transaction`
Immutable record of a financial event. Captures type (`DEPOSIT`, `WITHDRAWAL`, `TRANSFER_IN`, `TRANSFER_OUT`), amount, timestamp, and an optional related account for transfers.

### `InsufficientBalanceException` / `AccountNotFoundException`
Checked exceptions used for clean error handling in banking operations.

## Getting Started

### Prerequisites
- Java 8 or higher
- A terminal or IDE (IntelliJ, Eclipse, VS Code, etc.)

### Compile

```bash
javac *.java
```

### Run

```bash
java BankingSystem
```

## Sample Data

The application pre-loads two customers on startup:

| Customer ID | Name       | Account(s)          | Initial Balance     |
|-------------|------------|---------------------|---------------------|
| CUST001     | John Doe   | ACC1001 (SAVINGS)   | $1,000.00           |
|             |            | ACC1002 (CURRENT)   | $5,000.00           |
| CUST002     | Jane Smith | ACC2001 (SAVINGS)   | $2,500.00           |

## Usage

On launch, you are presented with a main menu:

```
Main Menu:
1. Customer Operations
2. Account Operations
3. Transaction Operations
4. Exit
```

Navigate the sub-menus to perform operations. All monetary values use `BigDecimal` for precision.

## Error Handling

| Scenario                        | Exception                      |
|---------------------------------|--------------------------------|
| Withdrawal exceeds balance      | `InsufficientBalanceException` |
| Transfer exceeds balance        | `InsufficientBalanceException` |
| Account lookup returns no match | Handled inline (returns `null`)|

## Limitations & Potential Improvements

- Data is in-memory only — no persistence between sessions
- No authentication or PIN protection
- Account number generation can produce duplicates across customers
- `AccountNotFoundException` is defined but not currently thrown (could replace `null` returns)
- No interest calculation for savings accounts
