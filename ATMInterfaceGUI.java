import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class BankAccount{
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false; // Insufficient balance
        }
        balance -= amount;
        return true;
    }

    public void deposit(double amount) {
        balance += amount;
    }
}

public class ATMInterfaceGUI {
    private BankAccount account;

    public ATMInterfaceGUI() {
        this.account = new BankAccount(1000.00); // Initial balance
        createGUI();
    }

    private void createGUI() {
        JFrame frame = new JFrame("ATM Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Output Area
        JTextArea outputArea = new JTextArea(5, 30);
        outputArea.setEditable(false);
        outputArea.setText("Welcome to the ATM!\nYour initial balance is ₹1000.00.\n");
        frame.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Input Panel
        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel amountLabel = new JLabel("Enter Amount:");
        JTextField amountField = new JTextField(10);
        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton checkBalanceButton = new JButton("Check Balance");

        inputPanel.add(amountLabel);
        inputPanel.add(amountField);
        inputPanel.add(withdrawButton);
        inputPanel.add(depositButton);
        inputPanel.add(checkBalanceButton);
        frame.add(inputPanel, BorderLayout.SOUTH);

        // Button Actions
        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    if (amount <= 0) {
                        outputArea.append("Error: Enter a positive amount.\n");
                    } else if (account.withdraw(amount)) {
                        outputArea.append("Withdrawn: ₹" + amount + "\n");
                        outputArea.append("Remaining Balance: ₹" + account.getBalance() + "\n");
                    } else {
                        outputArea.append("Error: Insufficient balance for withdrawal.\n");
                    }
                } catch (NumberFormatException ex) {
                    outputArea.append("Error: Please enter a valid number.\n");
                }
                amountField.setText("");
            }
        });

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    if (amount <= 0) {
                        outputArea.append("Error: Enter a positive amount to deposit.\n");
                    } else {
                        account.deposit(amount);
                        outputArea.append("Deposited: ₹" + amount + "\n");
                        outputArea.append("New Balance: ₹" + account.getBalance() + "\n");
                    }
                } catch (NumberFormatException ex) {
                    outputArea.append("Error: Please enter a valid number.\n");
                }
                amountField.setText("");
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                outputArea.append("Current Balance: ₹" + account.getBalance() + "\n");
            }
        });

        // Display the GUI
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ATMInterfaceGUI());
    }
}
