package com.example.my_project01;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class SearchStudent extends JFrame {

    private static final String url = "jdbc:mysql://127.0.0.1:3306/demo";
    private static final String userName = "root";
    private static final String password = "adithya@2004";

    private static final long serialVersionUID = 1L;
    private JTextField rollNumberField;
    private JButton searchButton, btnLoadData;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JPanel search;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SearchStudent frame = new SearchStudent();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public SearchStudent() {
        setTitle("Search Student");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        // Main Panel
        search = new JPanel();
        search.setBackground(new Color(240, 248, 255)); // Light Blue
        search.setLayout(null);
        setContentPane(search);

        // Title Label
        JLabel titleLabel = new JLabel("Search Student");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(170, 10, 200, 30);
        search.add(titleLabel);

        // Roll Number Label
        JLabel rollNumberLabel = new JLabel("Roll Number:");
        rollNumberLabel.setBounds(30, 60, 100, 25);
        search.add(rollNumberLabel);

        // Roll Number Input Field
        rollNumberField = new JTextField();
        rollNumberField.setBounds(150, 60, 200, 25);
        search.add(rollNumberField);

        // Search Button
        searchButton = new JButton("Search");
        searchButton.setBounds(359, 57, 100, 30);
        search.add(searchButton);

        // Load Data Button
        btnLoadData = new JButton("Load Data");
        btnLoadData.setBounds(30, 100, 100, 30);
        search.add(btnLoadData);

        // Table for displaying results
        String[] columnNames = {"ID", "Name", "Class"};
        tableModel = new DefaultTableModel(columnNames, 0);
        studentTable = new JTable(tableModel);
        studentTable.setEnabled(false);

        // Scroll Pane for Table
        JScrollPane tableScrollPane = new JScrollPane(studentTable);
        tableScrollPane.setBounds(30, 150, 420, 200);
        search.add(tableScrollPane);

        // Load Data Button - Fetches all students from DB
        btnLoadData.addActionListener(e -> loadAllStudents());

        // Search Button - Searches student by Roll Number
        searchButton.addActionListener(e -> searchStudent());
    }

    // Method to load all students
    private void loadAllStudents() {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver"); 	
        	 Connection con = DriverManager.getConnection(url, userName, password);
        	 Statement st = con.createStatement(); 
             PreparedStatement prst = con.prepareStatement("SELECT * FROM student");
             ResultSet set = prst.executeQuery();

            tableModel.setRowCount(0); // Clear previous data

            while (set.next()) {
                int id = set.getInt(1);
                String sid = Integer.toString(id); // convert to string
                String name = set.getString(2);
                String department = set.getString("department");

                String row[] = {sid, name, department};
                tableModel.addRow(row);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    // Method to search a student by roll number
    private void searchStudent() {
        String rollNumber = rollNumberField.getText().trim();

        if (rollNumber.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a roll number!");
            return;
        }

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver"); 
        	Connection con = DriverManager.getConnection(url, userName, password);
        	Statement st = con.createStatement(); 
        
             PreparedStatement prst = con.prepareStatement("SELECT * FROM student WHERE id = ?");

            prst.setInt(1, Integer.parseInt(rollNumber));
            ResultSet set = prst.executeQuery();

            tableModel.setRowCount(0); // Clear previous data

            if (set.next()) {
                int id = set.getInt(1);
                String name = set.getString(2);
                String department = set.getString("department");

                tableModel.addRow(new Object[]{id, name, department});
            } else {
                JOptionPane.showMessageDialog(null, "No student found with Roll Number: " + rollNumber);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }
}
