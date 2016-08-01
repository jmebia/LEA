package com.github.LEA;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * The main communication between the Model and LoginView
 */
public class Controller {

    private LoginView loginView;
    private MainView mainView;
    private Model model;

    public Controller(LoginView loginView, MainView mainView, Model model){
        this.loginView = loginView;
        this.mainView = mainView;
        this.model = model;

        this.loginView.addLoginButtonListener(new LoginButtonListener());
        this.mainView.addSendButtonListener(new SendButtonListener());
        this.mainView.addAttachmentButtonListener((new AttachmentButtonListener()));
    }

    // login button listener
    class LoginButtonListener implements ActionListener {

        // gets values from loginView
        // and logins the user through
        // methods from model

        public void actionPerformed(ActionEvent e){
            model.userLogin(loginView.getUserText(), loginView.getPassword());
            if(model.startSession()) {
                JOptionPane.showMessageDialog(null, "Login Successful!");
                loginView.dispose();
                mainView.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Login Failed!");
            }
        }
    }

    // send button listener
    class SendButtonListener implements ActionListener {

        // gets values from loginView
        // and logins the user through
        // methods from model

        public void actionPerformed(ActionEvent e){
            // sample addFile function
            // the file source path here is from my pc, you'll need to use your own
            // model.addFile("SampleFile","C:\\Users\\jmebia\\Desktop\\Sample.txt");

            model.sendMessage(mainView.getRecipient(), mainView.getSubject(), mainView.getMessage());
            JOptionPane.showMessageDialog(null, "Email Sent!");
            mainView.clearMessage();
        }
    }

    class AttachmentButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent e){
            try{
                FileChooser chooser = new FileChooser();
                String absPath = chooser.chooseFile();
                model.addFile("File", absPath);
                System.out.println("Attachment Added");
            } catch (Exception ex){
                System.out.println("Attachment Adding Failed");
            }

        }
    }

}