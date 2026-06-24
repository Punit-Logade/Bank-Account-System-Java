package bankAccount;

import java.util.HashMap;
import java.util.Map;

public class BankLogic {

    // Account Number → Account Object
    private Map<String, Account> accounts = new HashMap<>();

    /* ---------------- ACCOUNT CLASS ---------------- */

    static class Account {
        String name;
        String accountNumber;
        String pin;
        double balance;

        Account(String name, String accountNumber, String pin) {
            this.name = name;
            this.accountNumber = accountNumber;
            this.pin = pin;
            this.balance = 0.0;
        }
    }

    /* ---------------- CREATE ACCOUNT ---------------- */

    public String createAccount(String name, String accountNumber, String pin) {

        if (accounts.containsKey(accountNumber)) {
            return "Account already exists!";
        }

        Account newAccount = new Account(name, accountNumber, pin);
        accounts.put(accountNumber, newAccount);

        return "Account created successfully!";
    }

    /* ---------------- VALIDATE LOGIN ---------------- */

    public boolean validateAccount(String accountNumber, String pin) {

        if (!accounts.containsKey(accountNumber))
            return false;

        return accounts.get(accountNumber).pin.equals(pin);
    }

    /* ---------------- CHECK BALANCE ---------------- */

    public String checkBalance(String accountNumber, String pin) {

        if (!validateAccount(accountNumber, pin))
            return "Invalid Account or PIN";

        double balance = accounts.get(accountNumber).balance;
        return "Balance: ₹ " + balance;
    }

    /* ---------------- DEPOSIT ---------------- */

    public String deposit(String accountNumber, double amount) {

        if (!accounts.containsKey(accountNumber))
            return "Account not found";

        if (amount <= 0)
            return "Invalid deposit amount";

        Account acc = accounts.get(accountNumber);
        acc.balance += amount;

        return "Deposited ₹ " + amount + " Successfully";
    }

    /* ---------------- WITHDRAW ---------------- */

    public String withdraw(String accountNumber, String pin, double amount) {

        if (!validateAccount(accountNumber, pin))
            return "Invalid Account or PIN";

        Account acc = accounts.get(accountNumber);

        if (amount <= 0)
            return "Invalid withdrawal amount";

        if (acc.balance < amount)
            return "Insufficient balance";

        acc.balance -= amount;

        return "Withdrawn ₹ " + amount + " Successfully";
    }

    /* ---------------- CHANGE PIN ---------------- */

    public String changePin(String accountNumber, String oldPin, String newPin) {

        if (!validateAccount(accountNumber, oldPin))
            return "Invalid Account or PIN";

        accounts.get(accountNumber).pin = newPin;

        return "PIN changed successfully";
    }

    /* ---------------- GET ACCOUNT DETAILS ---------------- */

    public String getAccountDetails(String accountNumber) {

        if (!accounts.containsKey(accountNumber))
            return "Account not found";

        Account acc = accounts.get(accountNumber);

        return "Name: " + acc.name +
                "\nAccount No: " + acc.accountNumber +
                "\nBalance: ₹ " + acc.balance;
    }
}
