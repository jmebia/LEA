package com.project.lea; /**
 * all login form ui goes here
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame{

    // com.project.lea.LEA login components instantiation
    private JPanel panelLogin = new JPanel();
    private JTextField txtUser = new JTextField();
    private JPasswordField passPassword = new JPasswordField();
    private JButton btnLogin = new JButton();

    // constructor
    public LoginView(){
        this.setTitle("com.project.lea.LEA || Gmail Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(600, 300));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    // initializes the components for the login form
    public void initLoginComponents(){

        txtUser.setPreferredSize(new Dimension(250, 20));
        passPassword.setPreferredSize(new Dimension(250, 20));
        btnLogin.setPreferredSize(new Dimension(80,40));
        btnLogin.setText("Login");

        // GridBagLayout implemented
        panelLogin.setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();

        con.gridx = 0;
        con.gridy = 0;
        panelLogin.add(new JLabel("Email:"), con);
        con.gridx=1;
        con.gridy=0;
        panelLogin.add(txtUser, con);
        con.gridx = 0;
        con.gridy = 1;
        panelLogin.add(new Label("Password:"), con);
        con.gridx = 1;
        con.gridy = 1;
        panelLogin.add(passPassword, con);
        con.insets = new Insets(5,0,0,0);
        con.gridx = 1;
        con.gridy = 2;
        panelLogin.add(btnLogin,con);

        this.add(panelLogin);
        this.pack();
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
