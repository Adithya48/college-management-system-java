package com.example.my_project01;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Welcome extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel welcome;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Welcome frame = new Welcome();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Welcome() {
        setTitle("College Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 350);
        setLocationRelativeTo(null);
        setResizable(true); // Allow resizing
    


        // Create Main Panel
        welcome = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(74, 144, 226)); // Modern Light Blue
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        welcome.setLayout(null);
        setContentPane(welcome);

        // Title Label
        JLabel titleLabel = new JLabel("College Management System", SwingConstants.CENTER);
        titleLabel.setBackground(new Color(240, 240, 240));
        titleLabel.setFont(new Font("Serif", Font.BOLD, 22));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBounds(50, 30, 400, 30);
        welcome.add(titleLabel);

        // Welcome Label
        JLabel welcomeLabel = new JLabel("Welcome", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 18));
        welcomeLabel.setForeground(Color.BLACK);
        welcomeLabel.setBounds(180, 80, 150, 25);
        welcome.add(welcomeLabel);

        // Admin Button
        JButton adminButton = new JButton("Admin");
        adminButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Login login = new Login();
        		login.setVisible(true);
        		dispose();
        	}
        });
        styleButton(adminButton);
        adminButton.setBounds(130, 150, 100, 40);
        welcome.add(adminButton);

        // Search Button
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		SearchStudent search = new SearchStudent();
        		search.setVisible(true); 
//        		search.show();
        		dispose();
        	}
        });
        searchButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        searchButton.setForeground(Color.BLACK);
        searchButton.setEnabled(true);
        searchButton.setBackground(Color.CYAN);
        styleButton(searchButton);
        searchButton.setBounds(270, 150, 100, 40);
        welcome.add(searchButton);
    }

    // Button Styling
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(Color.CYAN); // Light grayish-white
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
