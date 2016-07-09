import javax.mail.MessagingException;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
    }

    // login button listener
    class LoginButtonListener implements ActionListener {

        // gets values from loginView
        // and logins the user through
        // methods from model

        public void actionPerformed(ActionEvent e){
            model.userLogin(loginView.getUserText(), loginView.getPassword());
            model.startSession();
            JOptionPane.showMessageDialog(null, "Login Successful!");
            loginView.dispose();
            mainView.setVisible(true);
        }
    }

    // send button listener
    class SendButtonListener implements ActionListener {

        // gets values from loginView
        // and logins the user through
        // methods from model

        public void actionPerformed(ActionEvent e){
            try {
                model.sendMessage(mainView.getRecipient(), mainView.getSubject(), mainView.getMessage());
            } catch (MessagingException me) {
                me.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Email Sent!");
            mainView.clearMessage();
        }
    }

}
