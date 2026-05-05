import bankutils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    static List<BankAccount> createAccounts(List<BankAccount> existingBankAccounts) {

        // When list parameter is null, construct a new arraylist
        List<BankAccount> bankAccounts;
        bankAccounts = Objects.requireNonNullElseGet(existingBankAccounts, ArrayList::new);

        char choice;
        do {
            String bankNumber;
            boolean validNumber = false;
            do {
                System.out.print("\nInsert a bank number (9 digits, numbers only): ");
                bankNumber = scanner.nextLine();
                if (!bankNumber.matches("\\d{9}")) {
                    System.out.println("\nInvalid bank number, please try again!");
                }
                else {
                    boolean duplicate = false;
                    // No accounts can have the same number, so we compare
                    for (BankAccount bankAccount : bankAccounts) {
                        if (bankAccount.getAccountNumber().equals(bankNumber)) {
                            duplicate = true;
                            break;
                        }
                    }
                    if (duplicate) {
                        System.out.println("An account with this number already exists!");
                    }
                    else {
                        validNumber = true;
                    }
                }
            } while (!validNumber);

            System.out.print("\nWhat is the name of the owner of this account? ");
            String ownerName = scanner.nextLine();

            System.out.print("\nWhat is the starting balance of this account? ");
            double balance = scanner.nextDouble();

            bankAccounts.add(new BankAccount(bankNumber, ownerName, balance));

            System.out.print("\nDo you want to create another account? (Y/N) ");
            choice = scanner.next().charAt(0);
            choice = Character.toLowerCase(choice);
            scanner.nextLine();

        } while (choice == 'y');

        return bankAccounts;
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

    public static void main(String[] args) {
        List<BankAccount> accounts = createPreExistingAccounts();
        Bank bank = new Bank(accounts);
    }
}
