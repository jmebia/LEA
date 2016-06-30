/**
 * Mail Class for LEA
 *
 * Created by jmebia on 29/06/2016.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Mail extends JPanel{

    // login info
    final String username = "email-goes-here@gmail.com";
    final String password = "password-goes-here";

    // email related
    private Properties props;
    private Session session;

    // swing components
    private JButton btnSend;
    private JTextArea txtMsg = new JTextArea(); // will contain the emails message
    private JTextField txtSubject = new JTextField(); // contains email subject
    private JTextField txtRecipient = new JTextField(); // contains email address of the recipient

    // Constructor
    public Mail() {

        // Setting up components
        this.setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();

        Dimension txtdim = new Dimension(150, 20);
        txtRecipient.setPreferredSize(txtdim);
        txtSubject.setPreferredSize(txtdim);
        txtMsg.setPreferredSize(new Dimension(400, 250));

        // recipient
        cons.gridx = 0;
        cons.gridy = 0;
        this.add(new JLabel("Recipient"),cons);
        cons.gridx = 0;
        cons.gridy = 1;
        this.add(txtRecipient, cons);

        // subject field
        cons.gridx = 0;
        cons.gridy = 2;
        this.add(new JLabel("Subject"),cons);
        cons.gridx = 0;
        cons.gridy = 3;
        this.add(txtSubject, cons);

        // message box
        cons.gridx = 0;
        cons.gridy = 4;
        this.add(new JLabel("Message:"),cons);
        cons.gridx = 0;
        cons.gridy = 5;
        this.add(txtMsg, cons);

        //sets up connection to server
        initConnection();

        // sets up the Send button
        btnSend = new JButton();
        btnSend.setText("Send");
        btnSend.setPreferredSize(new Dimension(100, 50));
        btnSend.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                System.out.print("Sending...");
                try {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(username));
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(txtRecipient.getText()));
                    message.setSubject(txtSubject.getText());
                    message.setText(txtMsg.getText());
                    Transport.send(message);

                    JOptionPane.showMessageDialog(null, "Email Sent");

                    // clears everything after email's sent
                    txtRecipient.setText("");
                    txtSubject.setText("");
                    txtMsg.setText("");

                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        cons.gridx = 0;
        cons.gridy = 6;
        this.add(btnSend, cons);
    }

    // handles connection and logins user to Gmail
    private void initConnection() {
        // connects to gmail
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); // smtp server address
        props.put("mail.smtp.port", "587"); // port for the smtp server
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        session = Session.getInstance(props,new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }


}

