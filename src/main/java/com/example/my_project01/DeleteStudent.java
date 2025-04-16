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
import java.sql.SQLException;
public class DeleteStudent extends JFrame {

    private static final String url = "jdbc:mysql://127.0.0.1:3306/demo";
    private static final String userName = "root";
    private static final String password = "adithya@2004";
    
    private static final long serialVersionUID = 1L;
    private JTextField rollNumberField;
    private JButton searchButton, deleteButton, loadAllButton;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                DeleteStudent frame = new DeleteStudent();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public DeleteStudent() {
        setTitle("Delete Student");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);  // Increased height for table
        setLocationRelativeTo(null);

        // Main Panel
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 248, 255)); // Light Blue
        contentPane.setLayout(null);  
        setContentPane(contentPane);

        // Title Label
        JLabel titleLabel = new JLabel("Delete Student");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(170, 10, 200, 30);
        contentPane.add(titleLabel);

        // Roll Number Label
        JLabel rollNumberLabel = new JLabel("Roll Number:");
        rollNumberLabel.setBounds(30, 60, 100, 25);
        contentPane.add(rollNumberLabel);

        // Roll Number Input Field
        rollNumberField = new JTextField();
        rollNumberField.setBounds(150, 60, 200, 25);
        contentPane.add(rollNumberField);

        // Search Button
        searchButton = new JButton("Search Student");
        searchButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 String rollNumber = rollNumberField.getText().trim();

        	        if (rollNumber.isEmpty()) {
        	            JOptionPane.showMessageDialog(null, "Please enter a roll number!");
        	            return;
        	        }

        	        try {
        	        	Class.forName("com.mysql.cj.jdbc.Driver"); 
        	        	Connection con = DriverManager.getConnection(url, userName, password);
//        	        	Statement st = con.createStatement(); 
        	        
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
        });
        searchButton.setBounds(360, 57, 116, 30);
        contentPane.add(searchButton);

        // Delete Button
        deleteButton = new JButton("Delete Student");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String rollNumber = rollNumberField.getText().trim();

                if (rollNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a roll number!");
                    return;
                }

                try {
                    int roll = Integer.parseInt(rollNumber);

                    Class.forName("com.mysql.cj.jdbc.Driver"); 
                    Connection con = DriverManager.getConnection(url, userName, password);

                    PreparedStatement prst = con.prepareStatement("DELETE FROM student WHERE id = ?");
                    prst.setInt(1, roll);

                    int rowsAffected = prst.executeUpdate();

                    if (rowsAffected > 0) {
                        tableModel.setRowCount(0); // Clear the table
                        JOptionPane.showMessageDialog(null, "Student with Roll Number: " + rollNumber + " has been deleted successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No student found with Roll Number: " + rollNumber);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid numeric roll number.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        deleteButton.setBounds(360, 97, 116, 30);
        contentPane.add(deleteButton);
        
        loadAllButton = new JButton("Load All Students");
        loadAllButton.setBounds(30, 111, 180, 30);
        contentPane.add(loadAllButton);
        
     // Load All Students Logic
        loadAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, userName, password);

                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM student");

                    tableModel.setRowCount(0); // Clear the table

                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String department = rs.getString("department");

                        tableModel.addRow(new Object[]{id, name, department});
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "SQL Error: " + ex.getMessage());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        // Table to Display Results
        String[] columnNames = {"ID", "Name", "Class"};
        tableModel = new DefaultTableModel(columnNames, 0); 
        studentTable = new JTable(tableModel);
        studentTable.setEnabled(false);  // Make table non-editable

        // Scroll Pane for Table
        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBounds(30, 150, 420, 200);
        contentPane.add(scrollPane);

    }
}
