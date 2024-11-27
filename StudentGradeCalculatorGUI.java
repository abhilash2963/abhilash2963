import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentGradeCalculatorGUI {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Student Grade Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        JLabel subject1Label = new JLabel("Subject 1 Marks:");
        JTextField subject1Field = new JTextField();
        JLabel subject2Label = new JLabel("Subject 2 Marks:");
        JTextField subject2Field = new JTextField();
        JLabel subject3Label = new JLabel("Subject 3 Marks:");
        JTextField subject3Field = new JTextField();
        JLabel subject4Label = new JLabel("Subject 4 Marks:");
        JTextField subject4Field = new JTextField();
        JLabel subject5Label = new JLabel("Subject 5 Marks:");
        JTextField subject5Field = new JTextField();

        inputPanel.add(subject1Label);
        inputPanel.add(subject1Field);
        inputPanel.add(subject2Label);
        inputPanel.add(subject2Field);
        inputPanel.add(subject3Label);
        inputPanel.add(subject3Field);
        inputPanel.add(subject4Label);
        inputPanel.add(subject4Field);
        inputPanel.add(subject5Label);
        inputPanel.add(subject5Field);

        frame.add(inputPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton calculateButton = new JButton("Calculate Grade");
        buttonPanel.add(calculateButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Output Area
        JTextArea outputArea = new JTextArea(5, 30);
        outputArea.setEditable(false);
        frame.add(new JScrollPane(outputArea), BorderLayout.NORTH);

        // Button Action
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Parse input marks
                    int subject1 = Integer.parseInt(subject1Field.getText());
                    int subject2 = Integer.parseInt(subject2Field.getText());
                    int subject3 = Integer.parseInt(subject3Field.getText());
                    int subject4 = Integer.parseInt(subject4Field.getText());
                    int subject5 = Integer.parseInt(subject5Field.getText());

                    // Validate marks
                    if (!isValidMarks(subject1) || !isValidMarks(subject2) || !isValidMarks(subject3) || !isValidMarks(subject4) || !isValidMarks(subject5)) {
                        outputArea.setText("Error: Marks must be between 0 and 100.");
                        return;
                    }

                    // Calculate total, average percentage, and grade
                    int totalMarks = subject1 + subject2 + subject3 + subject4 + subject5;
                    double averagePercentage = totalMarks / 5.0;
                    String grade = calculateGrade(averagePercentage);

                    // Display results
                    outputArea.setText("Results:\n");
                    outputArea.append("Total Marks: " + totalMarks + "\n");
                    outputArea.append("Average Percentage: " + String.format("%.2f", averagePercentage) + "%\n");
                    outputArea.append("Grade: " + grade + "\n");
                } catch (NumberFormatException ex) {
                    outputArea.setText("Error: Please enter valid numbers for all subjects.");
                }
            }
        });

        // Show the frame
        frame.setVisible(true);
    }

    // Method to validate marks
    private static boolean isValidMarks(int marks) {
        return marks >= 0 && marks <= 100;
    }

    // Method to calculate grade based on average percentage
    private static String calculateGrade(double percentage) {
        if (percentage >= 90) return "A+";
        else if (percentage >= 80) return "A";
        else if (percentage >= 70) return "B+";
        else if (percentage >= 60) return "B";
        else if (percentage >= 50) return "C";
        else if (percentage >= 40) return "D";
        else return "F";
    }
}
