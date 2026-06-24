package bankAccount;

public class BankAccount {

    private double balance;
    private String pin;

    public BankAccount(double initialBalance, String initialPin) {
        this.balance = initialBalance;
        this.pin = initialPin;
    }

    // ================= DEPOSIT =================
    public String deposit(String amountText) {

        if (amountText.isEmpty()) {
            return "Please enter amount to deposit.";
        }

        if (!amountText.matches("\\d+")) {
            return "Amount must contain numbers only.";
        }

        double amount = Double.parseDouble(amountText);

        if (amount <= 0) {
            return "Deposit amount must be greater than 0.";
        }

        balance += amount;

        return "₹" + amount + " deposited successfully.\nCurrent Balance: ₹" + balance;
    }

    // ================= WITHDRAW =================
    public String withdraw(String amountText, String enteredPin) {

        if (amountText.isEmpty() || enteredPin.isEmpty()) {
            return "Please enter amount and PIN.";
        }

        if (!enteredPin.equals(pin)) {
            return "Incorrect PIN.";
        }

        if (!amountText.matches("\\d+")) {
            return "Amount must contain numbers only.";
        }

        double amount = Double.parseDouble(amountText);

        if (amount <= 0) {
            return "Withdrawal amount must be greater than 0.";
        }

        if (amount > balance) {
            return "Insufficient balance.";
        }

        balance -= amount;

        return "₹" + amount + " withdrawn successfully.\nRemaining Balance: ₹" + balance;
    }

    // ================= CHECK BALANCE =================
    public String checkBalance(String enteredPin) {

        if (enteredPin.isEmpty()) {
            return "Please enter PIN.";
        }

        if (!enteredPin.equals(pin)) {
            return "Incorrect PIN.";
        }

        return "Current Balance: ₹" + balance;
    }

    // ================= CHANGE PIN =================
    public String changePin(String oldPin, String newPin, String confirmPin) {

        if (oldPin.isEmpty() || newPin.isEmpty() || confirmPin.isEmpty()) {
            return "Please fill all PIN fields.";
        }

        if (!oldPin.equals(pin)) {
            return "Incorrect Old PIN.";
        }

        if (!newPin.matches("\\d{4}")) {
            return "New PIN must be exactly 4 digits.";
        }

        if (newPin.equals(oldPin)) {
            return "New PIN cannot be same as old PIN.";
        }

        if (!newPin.equals(confirmPin)) {
            return "New PIN and Confirm PIN do not match.";
        }

        pin = newPin;

        return "PIN changed successfully.";
    }

    // ================= TRANSFER =================
    public String transfer(String amountText, String enteredPin) {

        if (amountText.isEmpty() || enteredPin.isEmpty()) {
            return "Please enter amount and PIN.";
        }

        if (!enteredPin.equals(pin)) {
            return "Incorrect PIN.";
        }

        if (!amountText.matches("\\d+")) {
            return "Amount must contain numbers only.";
        }

        double amount = Double.parseDouble(amountText);

        if (amount <= 0) {
            return "Transfer amount must be greater than 0.";
        }

        if (amount > balance) {
            return "Insufficient balance.";
        }

        balance -= amount;

        return "₹" + amount + " transferred successfully.\nRemaining Balance: ₹" + balance;
    }
}
