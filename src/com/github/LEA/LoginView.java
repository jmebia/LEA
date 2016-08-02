package com.github.LEA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame{

    // LEA login components instantiation
    private JPanel panelLogin = new JPanel();
    private JTextField txtUser = new JTextField();
    private JPasswordField passPassword = new JPasswordField();
    private JButton btnLogin = new JButton();

    // constructor
    public LoginView(){
        this.setTitle("LEA | Lightweight Email Application");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(400, 500));
        this.setResizable(false);
    }

    // initializes the components for the login form
    public void initLoginComponents(){

        // sets dimensions of text boxes and button
        txtUser.setPreferredSize(new Dimension(200, txtUser.getPreferredSize().height));
        passPassword.setPreferredSize(new Dimension(200, passPassword.getPreferredSize().height));
        btnLogin.setPreferredSize(new Dimension(80, 40));
        btnLogin.setText("Login");

        // Grid bag layout
        panelLogin.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        // adds components to JPanel with grid bag constraints
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        panelLogin.add(new Label("Email"), gc);
        gc.gridy++;
        panelLogin.add(txtUser, gc);
        gc.gridy++;
        panelLogin.add(new Label("Password"), gc);
        gc.gridy++;
        panelLogin.add(passPassword, gc);
        gc.gridy++;
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(10,0,0,0);
        panelLogin.add(btnLogin, gc);
        this.add(panelLogin);
        this.pack();
        this.setLocationRelativeTo(null); // sets frame to center screen
    }

    /*----------- Getters ------------*/
    public String getUserText(){
        return txtUser.getText();
    }

    public char[] getPassword(){
        return passPassword.getPassword();
    }

    // login button action listener
    void addLoginButtonListener(ActionListener loginButtonListener){
        btnLogin.addActionListener(loginButtonListener);
    }
}