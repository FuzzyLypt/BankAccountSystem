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

    public BankAccount searchAccount(String accountNumber) {
        if (!accounts.isEmpty()) {
            for (BankAccount account : accounts) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    System.out.println("\nAccount found!");
                    account.showInfo();
                    return account;
                }
            }
        }
        System.out.println("\nAccount not found!");
        return null;
    }

    public void deleteAccount(BankAccount account) {
        accounts.remove(account);
        System.out.println("\nAccount deleted!");
    }

}
