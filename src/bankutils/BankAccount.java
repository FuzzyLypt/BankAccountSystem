package bankutils;

public class BankAccount {
    // Encapsulated variables
    private String accountNumber;
    private String owner;
    private double balance;

    // Constructor
    public BankAccount(String accountNumber, String owner, double balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }

    // Getters and Setters
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Method for showing general info about the account
    public void showInfo() {
        System.out.println("\nAccount Number: " + accountNumber);
        System.out.println("Owner: " + owner);
        System.out.printf("Balance: $%.2f\n", balance);
    }

    // Methods for changing account balance
    public void deposit(double amount) {
        this.balance += amount;
        System.out.println("Deposited $" + amount);
    }

    public void withdraw(double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            System.out.println("Withdrawn $" + amount);
        }
        else  {
            System.out.println("\nInsufficient funds!");
        }
    }
}
