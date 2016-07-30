package com.github.LEA;

import javafx.geometry.VerticalDirection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

    // LEA main components instantiation
    private JPanel panelMain = new JPanel();
    private JTextField txtRecipient = new JTextField();
    private JTextField txtSubject = new JTextField();
    private JTextArea txtMessage = new JTextArea();
    private JButton btnSend = new JButton();

    private JScrollPane scrollPane;
    // constructor
    public MainView(){
        this.setTitle("LEA | Lightweight Email Application");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(600,500));
    }

    // initializes the components for the main app
    public void initMainComponents(){



        // swing component set up
        txtRecipient.setPreferredSize(new Dimension(300, txtRecipient.getPreferredSize().height));
        txtSubject.setPreferredSize(new Dimension(300, txtSubject.getPreferredSize().height));
        scrollPane = new JScrollPane(txtMessage);
        scrollPane.setPreferredSize(new Dimension(300, 200));
        txtMessage.setLineWrap(true);
        btnSend.setPreferredSize(new Dimension(100, 50));
        btnSend.setText("Send");

        // GridBagLayout
        panelMain.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        // add components to main panel
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        panelMain.add(new Label("Send to"), gc);
        gc.gridy++;
        panelMain.add(txtRecipient, gc);
        gc.gridy++;
        panelMain.add(new Label("Subject"), gc);
        gc.gridy++;
        panelMain.add(txtSubject, gc);
        gc.gridy++;
        gc.insets = new Insets(10,0,0,0);
        panelMain.add(scrollPane, gc);
        gc.gridy++;
        gc.anchor = GridBagConstraints.CENTER;
        panelMain.add(btnSend, gc);
        this.add(panelMain);
        this.pack();
        this.setLocationRelativeTo(null); // sets frame to center screen
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

    // send button action listener
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
