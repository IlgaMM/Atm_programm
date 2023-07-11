package Atm;

import javax.swing.JOptionPane;

public class BankController {
    private Login login;
    private UserRepository userRepository;

    public BankController() {
        login = new Login();
        userRepository = new UserRepository();
        userRepository.loadLoginData();
    }

    public void start() {
        boolean running = true;
        while (running) {
            String choice = JOptionPane.showInputDialog("Welcome to the Bank!\n1. Register\n2. Login\n3. Exit");

            if (choice != null) {
                switch (choice) {
                    case "1":
                        register();
                        break;
                    case "2":
                        login();
                        break;
                    case "3":
                        running = false;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
                        break;
                }
            } else {
                running = false;
            }
        }
    }

    private void register() {
        User newUser = login.registerUser();
        if (newUser != null) {
            userRepository.addUser(newUser);
            JOptionPane.showMessageDialog(null, "Registration successful. Your account number is: " + newUser.getAccountNumber());
            showUserMenu(newUser);
        }
    }

    private void login() {
        User user = login.loginUser();
        if (user != null) {
            showUserMenu(user);
        }
    }

    private void showUserMenu(User user) {
        boolean loggedIn = true;
        while (loggedIn) {
            String choice = JOptionPane.showInputDialog("Welcome, " + user.getName() + "!\n1. Credit\n2. Debit\n3. Show Balance\n4. Logout");

            if (choice != null) {
                switch (choice) {
                    case "1":
                        double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter the amount to credit:"));
                        user.credit(amount);
                        break;
                    case "2":
                        amount = Double.parseDouble(JOptionPane.showInputDialog("Enter the amount to debit:"));
                        user.debit(amount);
                        break;
                    case "3":
                        JOptionPane.showMessageDialog(null, "Your balance is: $" + user.getBalance());
                        break;
                    case "4":
                        loggedIn = false;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
                        break;
                }
            } else {
                loggedIn = false;
            }
        }
    }
}