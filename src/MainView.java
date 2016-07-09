/**
 * all ui of the main app goes here
 */

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

    // constructor
    public MainView(){
        this.setTitle("LEA || Lightweight Email Application || Powered by Gmail");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(600,500));
    }

    // initializes the components for the main app
    public void initMainComponents(){

        txtRecipient.setPreferredSize(new Dimension(150, 20));
        txtSubject.setPreferredSize(new Dimension(150, 20));
        txtMessage.setPreferredSize(new Dimension(250, 200));
        btnSend.setPreferredSize(new Dimension(100, 50));
        btnSend.setText("Send");

        // final touches
        panelMain.add(txtRecipient);
        panelMain.add(txtSubject);
        panelMain.add(txtMessage);
        panelMain.add(btnSend);
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
