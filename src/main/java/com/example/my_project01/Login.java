package com.example.my_project01;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usn;
    private JPasswordField upass;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Login frame = new Login();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Login() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 350);
        setResizable(true); // Disable maximize
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(30, 144, 255)); // Modern Blue
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblTitle = new JLabel("Login");
        lblTitle.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(200, 30, 100, 30);
        contentPane.add(lblTitle);
        
        JLabel lblUsername = new JLabel("User Name");
        lblUsername.setFont(new Font("Arial", Font.BOLD, 14));
        lblUsername.setForeground(Color.WHITE);
        lblUsername.setBounds(80, 90, 100, 20);
        contentPane.add(lblUsername);
        
        usn = new JTextField();
        usn.setBounds(200, 90, 200, 25);
        contentPane.add(usn);
        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Arial", Font.BOLD, 14));
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setBounds(80, 140, 100, 20);
        contentPane.add(lblPassword);
        
        upass = new JPasswordField();
        upass.setBounds(200, 140, 200, 25);
        contentPane.add(upass);
        
        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            String Username = "Admin";
            String password = "1234";

            public void actionPerformed(ActionEvent e) {
                String un = usn.getText();
                String pas = new String(upass.getPassword());

                if (un.equals(Username) && pas.equals(password)) {
                    Admin admin = new Admin();
                    admin.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Username or Password!!");
                }
            }
        });

        btnLogin.setBounds(200, 200, 100, 30);
        btnLogin.setBackground(Color.WHITE);
        contentPane.add(btnLogin);
    }
}
