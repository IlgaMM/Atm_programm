package Atm;

import javax.swing.*;

public class User {
    private String accountNumber;
    private double balance;
    private String name;
    private Gender gender;
    private String email;
    private String pin;

    public User(String name, Gender gender, String email, String pin) {
        this.accountNumber = generateAccountNumber();
        this.balance = 0.0;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.pin = pin;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPin() {
        return pin;
    }

    public void credit(double amount) {
        balance += amount;
        JOptionPane.showMessageDialog(null, "Amount credited successfully.");
    }

    public void debit(double amount) {
        if (amount <= balance) {
            balance -= amount;
            JOptionPane.showMessageDialog(null, "Amount debited successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "Insufficient balance.");
        }
    }

    private static String generateAccountNumber() {
        // Generate a random account number
        return "ACC" + System.currentTimeMillis();
    }
}