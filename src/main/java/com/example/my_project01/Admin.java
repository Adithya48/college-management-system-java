package com.example.my_project01;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel Admin;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Admin frame = new Admin();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Admin() {
        setTitle("Admin Panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        setResizable(false);

        Admin = new JPanel();
        Admin.setBackground(new Color(44, 62, 80)); // Modern dark blue theme
        Admin.setLayout(null);
        setContentPane(Admin);

        JLabel lblTitle = new JLabel("Admin Panel");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setBounds(176, 26, 200, 30);
        Admin.add(lblTitle);

        JButton btnAdd = new JButton("Add Student");
        btnAdd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AddStudent add = new AddStudent();
        		add.setVisible(true);
        		dispose();
        	}
        });
        btnAdd.setName("AddStudent");
        btnAdd.setBounds(150, 90, 200, 40);
        Admin.add(btnAdd);
        
        JButton btnDelete = new JButton("Delete Student");
        btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		DeleteStudent delete = new DeleteStudent();
        		delete.setVisible(true);
        		dispose();
        	}
        });
        btnDelete.setName("DeleteStudent");
        btnDelete.setBounds(150, 140, 200, 40);
        Admin.add(btnDelete);
        
        JButton btnSearch = new JButton("Search Student");
        btnSearch.setName("SearchStudent");
        btnSearch.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		SearchStudent search = new SearchStudent();
        		search.setVisible(true);
        		dispose();
        	}
        });
        btnSearch.setBounds(150, 190, 200, 40);
        Admin.add(btnSearch);
        
        JButton btnUpdate = new JButton("Update Student");
        btnUpdate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		UpdateStudent update = new UpdateStudent();
        		update.setVisible(true);
        		dispose();
        	}
        });
        btnUpdate.setName("UpdateStudent");
        btnUpdate.setBounds(150, 240, 200, 40);
        Admin.add(btnUpdate);
//
//        btnAdd.addActionListener(e -> JOptionPane.showMessageDialog(this, "Add Student Clicked"));
//        btnDelete.addActionListener(e -> JOptionPane.showMessageDialog(this, "Delete Student Clicked"));
//        btnSearch.addActionListener(e -> JOptionPane.showMessageDialog(this, "Search Student Clicked"));
//        btnUpdate.addActionListener(e -> JOptionPane.showMessageDialog(this, "Update Student Clicked"));
    }
}
