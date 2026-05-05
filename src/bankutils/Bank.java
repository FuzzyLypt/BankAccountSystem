package bankutils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bank {
    // Encapsulated variable
    private List<BankAccount> accounts;

    // Constructor
    public Bank(List<BankAccount> bankAccounts) {
        this.accounts = Objects.requireNonNullElseGet(bankAccounts, ArrayList::new);
    }

    // Methods related to the accounts
    public void addAccount(BankAccount account) {
        this.accounts.add(account);
        System.out.println("\nAccount added!");
    }

    public void listAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("\nNo accounts found!");
        }
        else {
            for (BankAccount account : accounts) {
                account.showInfo();
            }
        }
    }

    public void searchAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                System.out.println("Account found!");
                account.showInfo();
            }
        }
    }

    public void deleteAccount(String accountNumber) {
        accounts.removeIf(account -> account.getAccountNumber().equals(accountNumber));
    }

}
