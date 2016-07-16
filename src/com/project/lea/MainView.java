package com.project.lea; /**
 * all ui of the main app goes here
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

    // com.project.lea.LEA main components instantiation
    private JPanel panelMain = new JPanel();
    private JTextField txtRecipient = new JTextField();
    private JTextField txtSubject = new JTextField();
    private JTextArea txtMessage = new JTextArea();
    private JButton btnSend = new JButton();

    // constructor
    public MainView(){
        this.setTitle("LEA | Lightweight Email Application || Powered by Gmail");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(650,500));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    // initializes the components for the main app
    public void initMainComponents(){

        txtRecipient.setPreferredSize(new Dimension(450, 20));
        txtSubject.setPreferredSize(new Dimension(450, 20));
        txtMessage.setPreferredSize(new Dimension(450, 200));
        btnSend.setPreferredSize(new Dimension(100, 60));
        btnSend.setText("Send");

        //PromptSupport.setPrompt("recipient@gmail.com", txtRecipient);

        // GridBagLayout implemented
        panelMain.setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();

        // arranges components with GridBagLayout
        con.gridx = 0;
        con.gridy = 0;
        panelMain.add(new Label("Send To:"), con);
        con.gridx = 1;
        con.gridy = 0;
        panelMain.add(txtRecipient,con);
        con.gridx = 0;
        con.gridy = 1;
        panelMain.add(new Label("Subject:"), con);
        con.gridx = 1;
        con.gridy = 1;
        panelMain.add(txtSubject, con);
        con.insets = new Insets(10,0,0,0); // top padding for message box and send button
        con.gridx = 1;
        con.gridy = 2;
        panelMain.add(txtMessage, con);
        con.gridx = 1;
        con.gridy = 3;
        panelMain.add(btnSend, con);

        this.add(panelMain);
        this.pack();
    }

    /*---------- Getters -----------*/
    public String getRecipient(){
        return txtRecipient.getText();
    }

    public String getSubject(){
        return txtSubject.getText();
    }

    public String getMessage(){
        return txtMessage.getText();
    }

    // action listener for send button
    void addSendButtonListener(ActionListener sendButtonListener){
        btnSend.addActionListener(sendButtonListener);
    }

    // cleaner
    void clearMessage(){
        txtRecipient.setText("");
        txtSubject.setText("");
        txtMessage.setText("");
    }

}
