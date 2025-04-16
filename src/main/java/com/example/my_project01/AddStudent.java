package com.example.my_project01;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class AddStudent extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final String url = "jdbc:mysql://127.0.0.1:3306/demo";
    private static final String userName = "root";
    private static final String password = "adithya@2004";

    private JTextField nameField, ageField, departmentField, marksField;
    private JComboBox<String> genderBox;
    private JButton addButton;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AddStudent frame = new AddStudent();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AddStudent() {
        setTitle("Add Student");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 400);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 248, 255));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel titleLabel = new JLabel("Add Student");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(160, 10, 200, 30);
        contentPane.add(titleLabel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(30, 60, 100, 25);
        contentPane.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 60, 230, 25);
        contentPane.add(nameField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(30, 100, 100, 25);
        contentPane.add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(150, 100, 230, 25);
        contentPane.add(ageField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(30, 140, 100, 25);
        contentPane.add(genderLabel);

        String[] genders = {"Male", "Female", "Other"};
        genderBox = new JComboBox<>(genders);
        genderBox.setBounds(150, 140, 230, 25);
        contentPane.add(genderBox);

        JLabel departmentLabel = new JLabel("Department:");
        departmentLabel.setBounds(30, 180, 100, 25);
        contentPane.add(departmentLabel);

        departmentField = new JTextField();
        departmentField.setBounds(150, 180, 230, 25);
        contentPane.add(departmentField);

        JLabel marksLabel = new JLabel("Marks:");
        marksLabel.setBounds(30, 220, 100, 25);
        contentPane.add(marksLabel);

        marksField = new JTextField();
        marksField.setBounds(150, 220, 230, 25);
        contentPane.add(marksField);

        addButton = new JButton("Add Student");
        addButton.setBounds(150, 270, 150, 35);
        contentPane.add(addButton);

        // Button Action
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String ageText = ageField.getText().trim();
                String gender = genderBox.getSelectedItem().toString();
                String department = departmentField.getText().trim();
                String marksText = marksField.getText().trim();

                if (name.isEmpty() || ageText.isEmpty() || department.isEmpty() || marksText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields.");
                    return;
                }

                try {
                    int age = Integer.parseInt(ageText);
                    int marks = Integer.parseInt(marksText);

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, userName, password);

                    String query = "INSERT INTO student (name, age, gender, department, marks) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, name);
                    ps.setInt(2, age);
                    ps.setString(3, gender);
                    ps.setString(4, department);
                    ps.setInt(5, marks);

                    int rowsInserted = ps.executeUpdate();

                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(null, "Student added successfully!");
                        clearFields();
                    }

                    con.close();
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numeric values for age and marks.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });
    }

    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        departmentField.setText("");
        marksField.setText("");
        genderBox.setSelectedIndex(0);
    }
}
