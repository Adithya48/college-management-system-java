package com.example.my_project01;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class UpdateStudent extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final String url = "jdbc:mysql://127.0.0.1:3306/demo";
    private static final String userName = "root";
    private static final String password = "adithya@2004";

    private JTextField rollField, nameField, ageField, departmentField, marksField;
    private JComboBox<String> genderBox;
    private JButton updateButton;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UpdateStudent frame = new UpdateStudent();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public UpdateStudent() {
        setTitle("Update Student");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 248, 255));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel titleLabel = new JLabel("Update Student");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(120, 10, 200, 30);
        contentPane.add(titleLabel);

        // Roll Number
        JLabel rollLabel = new JLabel("Roll Number:");
        rollLabel.setBounds(30, 50, 100, 25);
        contentPane.add(rollLabel);

        rollField = new JTextField();
        rollField.setBounds(140, 50, 200, 25);
        contentPane.add(rollField);

        // Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(30, 90, 100, 25);
        contentPane.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(140, 90, 200, 25);
        contentPane.add(nameField);

        // Age
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(30, 130, 100, 25);
        contentPane.add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(140, 130, 200, 25);
        contentPane.add(ageField);

        // Gender
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(30, 170, 100, 25);
        contentPane.add(genderLabel);

        String[] genders = {"Male", "Female", "Other"};
        genderBox = new JComboBox<>(genders);
        genderBox.setBounds(140, 170, 200, 25);
        contentPane.add(genderBox);

        // Department
        JLabel departmentLabel = new JLabel("Department:");
        departmentLabel.setBounds(30, 210, 100, 25);
        contentPane.add(departmentLabel);

        departmentField = new JTextField();
        departmentField.setBounds(140, 210, 200, 25);
        contentPane.add(departmentField);

        // Marks
        JLabel marksLabel = new JLabel("Marks:");
        marksLabel.setBounds(30, 250, 100, 25);
        contentPane.add(marksLabel);

        marksField = new JTextField();
        marksField.setBounds(140, 250, 200, 25);
        contentPane.add(marksField);

        // Update Button
        updateButton = new JButton("Update Student");
        updateButton.setBounds(120, 300, 150, 30);
        contentPane.add(updateButton);

        // Action Listener for Update Button
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String rollText = rollField.getText().trim();
                String name = nameField.getText().trim();
                String ageText = ageField.getText().trim();
                String gender = genderBox.getSelectedItem().toString();
                String department = departmentField.getText().trim();
                String marksText = marksField.getText().trim();

                if (rollText.isEmpty() || name.isEmpty() || ageText.isEmpty() || department.isEmpty() || marksText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                    return;
                }

                try {
                    int roll = Integer.parseInt(rollText);
                    int age = Integer.parseInt(ageText);
                    double marks = Double.parseDouble(marksText);

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, userName, password);

                    String query = "UPDATE student SET name = ?, age = ?, gender = ?, department = ?, marks = ? WHERE id = ?";
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, name);
                    ps.setInt(2, age);
                    ps.setString(3, gender);
                    ps.setString(4, department);
                    ps.setDouble(5, marks);
                    ps.setInt(6, roll);

                    int rowsUpdated = ps.executeUpdate();

                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(null, "Student details updated successfully!");
                        clearFields();
                    } else {
                        JOptionPane.showMessageDialog(null, "No student found with Roll Number: " + roll);
                    }

                    con.close();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numeric values for Age and Marks.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });
    }
    private void clearFields() {
    	rollField.setText("");
        nameField.setText("");
        ageField.setText("");
        departmentField.setText("");
        marksField.setText("");
        genderBox.setSelectedIndex(0);
    }
}
