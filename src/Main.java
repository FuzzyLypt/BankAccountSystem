import bankutils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    static String bankNumberValidator() {
        // All bank numbers must have a format
        String bankNumber;
        boolean validNumber = false;
        do {
            System.out.print("\nInsert a bank number (9 digits, numbers only): ");
            bankNumber = scanner.nextLine();
            if (!bankNumber.matches("\\d{9}")) {
                System.out.println("\nInvalid bank number, please try again!");
            } else {
                validNumber = true;
            }
        } while (!validNumber);
        return bankNumber;
    }

    static boolean bankNumberDuplicateFinder (List<BankAccount> bankAccounts, String bankNumber) {
        // No duplicated bank numbers, bank numbers serve as an unique identifier for each account
        boolean duplicate = false;
        for (BankAccount bankAccount : bankAccounts) {
            if (bankAccount.getAccountNumber().equals(bankNumber)) {
                duplicate = true;
                break;
            }
        }
        if (duplicate) {
            System.out.println("An account with this number already exists!");
            return false;
        }
        else {
            return true;
        }
    }

    static int typeValidator() {
        boolean validationCheck = false;
        int type;
        do {
            System.out.println("""
                    \nWhat is the type of this account?
                    
                    1 - Checking account
                    2 - Saving account
                    """);
            type = scanner.nextInt();

            switch (type) {
                case 1, 2:
                    validationCheck = true;
                    break;
                default:
                    System.out.println("\nInvalid type, please try again!");
                    break;
            }
        } while (!validationCheck);
        return type;
    }

    static List<BankAccount> createAccounts(List<BankAccount> existingBankAccounts) {

        List<BankAccount> createdBankAccounts = new ArrayList<>();
        existingBankAccounts = Objects.requireNonNullElseGet(existingBankAccounts, ArrayList::new);

        char choice;
        do {
            // This is used to find any duplicated numbers by both existent and created lists in the duplicate finder
            List<BankAccount> tempTotalAccounts = new ArrayList<>(existingBankAccounts);
            tempTotalAccounts.addAll(createdBankAccounts);
            String bankNumber;
            boolean validNumber;
            do {
                bankNumber = bankNumberValidator();
                validNumber = bankNumberDuplicateFinder(tempTotalAccounts, bankNumber);
            } while (!validNumber);

            System.out.print("\nWhat is the name of the owner of this account? ");
            String ownerName = scanner.nextLine();

            System.out.print("\nWhat is the starting balance of this account? ");
            double balance = scanner.nextDouble();

            int type = typeValidator();

            createdBankAccounts.add(new BankAccount(bankNumber, ownerName, balance, type));

            System.out.print("\nDo you want to create another account? (Y/N) ");
            choice = scanner.next().charAt(0);
            choice = Character.toLowerCase(choice);
            scanner.nextLine();

        } while (choice == 'y');

        return createdBankAccounts;
    }

    static List<BankAccount> createPreExistingAccounts() {
        System.out.print("\nDo you want to create pre-existing bank accounts? (Y/N) ");
        char preChoice = scanner.next().charAt(0);
        preChoice = Character.toLowerCase(preChoice);
        scanner.nextLine();

        if (preChoice == 'y') {
            return createAccounts(null);
        }
        else {
            return null;
        }
    }

    static BankAccount searchAndValidateAccount(Bank bank) {
        String bankNumber = bankNumberValidator();
        return bank.searchAccount(bankNumber);
    }

    public static void main(String[] args) {
        List<BankAccount> accounts = createPreExistingAccounts();
        Bank bank = new Bank(accounts);

        while (true) {
            System.out.println("""
                \n--- --- --- --- ---
                
                Welcome to the Bank Account system, please choose one of the options:
                
                1 - Add more accounts to the bank
                
                2 - List accounts
                
                3 - Deposit money
                
                4 - Withdraw money
                
                5 - Search for a specific account
                
                6 - Delete an account
                
                0 - Exit the program
                """);
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                {
                    List<BankAccount> createdAccounts = new ArrayList<>(createAccounts(accounts));
                    for (BankAccount bankAccount : createdAccounts) {
                        bank.addAccount(bankAccount);
                    }
                }
                    break;
                case 2:
                    bank.listAccounts();
                    break;
                case 3:
                {
                    BankAccount foundAccount = searchAndValidateAccount(bank);
                    System.out.print("\nEnter amount to deposit: ");
                    foundAccount.deposit(scanner.nextDouble());
                }
                    break;
                case 4:
                {
                    BankAccount foundAccount = searchAndValidateAccount(bank);
                    System.out.print("\nEnter amount to withdraw: ");
                    foundAccount.withdraw(scanner.nextDouble());
                }
                    break;
                case 5:
                    searchAndValidateAccount(bank);
                    break;
                case 6:
                    bank.deleteAccount(searchAndValidateAccount(bank));
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
        }

    }
}
