import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NumberGuessingGameGUI {
    private JFrame frame;
    private JTextField guessField;
    private JTextArea outputArea;
    private JButton guessButton, newGameButton;
    private int targetNumber;
    private int attemptsLeft;
    private int score;

    public NumberGuessingGameGUI() {
        initializeGame();
        createGUI();
    }

    private void initializeGame() {
        targetNumber = new Random().nextInt(100) + 1; // Generate a number between 1 and 100
        attemptsLeft = 5; // Limit the number of attempts
        score = 0; // Initial score
    }

    private void createGUI() {
        // Set up the main frame
        frame = new JFrame("Number Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Output area for game messages
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setText("Welcome to the Number Guessing Game!\n");
        outputArea.append("Guess a number between 1 and 100.\n");
        outputArea.append("You have 5 attempts.\n");
        frame.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Input and control panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel guessLabel = new JLabel("Your Guess:");
        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        newGameButton = new JButton("New Game");
        newGameButton.setEnabled(false); // Disable until the round ends

        inputPanel.add(guessLabel);
        inputPanel.add(guessField);
        inputPanel.add(guessButton);
        inputPanel.add(newGameButton);
        frame.add(inputPanel, BorderLayout.SOUTH);

        // Add button actions
        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });

        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });

        frame.setVisible(true);
    }

    private void handleGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            if (guess < 1 || guess > 100) {
                outputArea.append("Please enter a number between 1 and 100.\n");
                return;
            }

            attemptsLeft--;
            if (guess == targetNumber) {
                outputArea.append("Correct! The number was " + targetNumber + ".\n");
                score++;
                endRound(true);
            } else if (attemptsLeft > 0) {
                if (guess < targetNumber) {
                    outputArea.append("Too low! Try again. Attempts left: " + attemptsLeft + "\n");
                } else {
                    outputArea.append("Too high! Try again. Attempts left: " + attemptsLeft + "\n");
                }
            } else {
                outputArea.append("Game over! The correct number was " + targetNumber + ".\n");
                endRound(false);
            }

            guessField.setText(""); // Clear the input field
        } catch (NumberFormatException ex) {
            outputArea.append("Invalid input. Please enter a valid number.\n");
        }
    }

    private void endRound(boolean won) {
        if (won) {
            outputArea.append("Congratulations! You've won this round.\n");
        } else {
            outputArea.append("Better luck next time.\n");
        }
        outputArea.append("Score: " + score + "\n");
        guessButton.setEnabled(false);
        newGameButton.setEnabled(true);
    }

    private void startNewGame() {
        targetNumber = new Random().nextInt(100) + 1;
        attemptsLeft = 5;
        outputArea.append("\nNew round started! Guess a number between 1 and 100.\n");
        guessButton.setEnabled(true);
        newGameButton.setEnabled(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NumberGuessingGameGUI();
            }
        });
    }
}
