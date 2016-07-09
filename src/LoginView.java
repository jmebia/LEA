/**
 * all login form ui goes here
 */

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
        this.setTitle("LEA || Gmail Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(300, 400));
    }

    // initializes the components for the login form
    public void initLoginComponents(){

        txtUser.setPreferredSize(new Dimension(70, 20));
        passPassword.setPreferredSize(new Dimension(70, 20));
        btnLogin.setPreferredSize(new Dimension(100, 50));
        btnLogin.setText("Login");

        // final touches
        panelLogin.add(txtUser);
        panelLogin.add(passPassword);
        panelLogin.add(btnLogin);
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
