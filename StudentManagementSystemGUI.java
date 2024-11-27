import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;  // Import ArrayList from java.util
import java.util.List;      // Import List from java.util

public class StudentManagementSystemGUI {
    public static void main(String[] args) {
        // Create the main JFrame
        JFrame frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Create the list to hold the students
        List<Student> students = new ArrayList<>();  // Use List from java.util

        // Create panels for the student management system
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        // Create components for adding student
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel rollLabel = new JLabel("Roll Number:");
        JTextField rollField = new JTextField();
        JLabel gradeLabel = new JLabel("Grade:");
        JTextField gradeField = new JTextField();
        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField();

        // Add components to the panel
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(rollLabel);
        panel.add(rollField);
        panel.add(gradeLabel);
        panel.add(gradeField);
        panel.add(ageLabel);
        panel.add(ageField);

        // Create buttons
        JButton addButton = new JButton("Add Student");
        JButton removeButton = new JButton("Remove Student");
        JButton searchButton = new JButton("Search Student");
        JButton displayButton = new JButton("Display All Students");

        // Add the panel and buttons to the frame
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(displayButton);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Create a text area for displaying output
        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);
        frame.add(new JScrollPane(displayArea), BorderLayout.EAST);

        // Action for Add Student Button
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String rollNumber = rollField.getText();
                String grade = gradeField.getText();
                int age = Integer.parseInt(ageField.getText());

                if (!name.isEmpty() && !rollNumber.isEmpty() && !grade.isEmpty() && age > 0) {
                    Student newStudent = new Student(name, rollNumber, grade, age);
                    students.add(newStudent);
                    displayArea.setText("Student added successfully:\n" + newStudent.displayInfo());
                    clearFields(nameField, rollField, gradeField, ageField);
                } else {
                    displayArea.setText("Please fill all fields with valid data.");
                }
            }
        });

        // Action for Remove Student Button
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String rollNumber = JOptionPane.showInputDialog(frame, "Enter roll number to remove:");
                boolean removed = false;
                for (Student student : students) {
                    if (student.getRollNumber().equals(rollNumber)) {
                        students.remove(student);
                        removed = true;
                        break;
                    }
                }
                if (removed) {
                    displayArea.setText("Student with roll number " + rollNumber + " removed.");
                } else {
                    displayArea.setText("Student not found.");
                }
            }
        });

        // Action for Search Student Button
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String rollNumber = JOptionPane.showInputDialog(frame, "Enter roll number to search:");
                Student foundStudent = null;
                for (Student student : students) {
                    if (student.getRollNumber().equals(rollNumber)) {
                        foundStudent = student;
                        break;
                    }
                }
                if (foundStudent != null) {
                    displayArea.setText(foundStudent.displayInfo());
                } else {
                    displayArea.setText("Student not found.");
                }
            }
        });

        // Action for Display All Students Button
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (students.isEmpty()) {
                    displayArea.setText("No students found.");
                } else {
                    StringBuilder allStudents = new StringBuilder();
                    for (Student student : students) {
                        allStudents.append(student.displayInfo()).append("\n-----------------------------\n");
                    }
                    displayArea.setText(allStudents.toString());
                }
            }
        });

        // Show the frame
        frame.setVisible(true);
    }

    // Method to clear input fields
    public static void clearFields(JTextField nameField, JTextField rollField, JTextField gradeField, JTextField ageField) {
        nameField.setText("");
        rollField.setText("");
        gradeField.setText("");
        ageField.setText("");
    }
}

// Student class to represent individual students
class Student {
    private String name;
    private String rollNumber;
    private String grade;
    private int age;

    // Constructor
    public Student(String name, String rollNumber, String grade, int age) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.age = age;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Method to display student information
    public String displayInfo() {
        return "Name: " + name + "\nRoll Number: " + rollNumber + "\nGrade: " + grade + "\nAge: " + age;
    }
}
