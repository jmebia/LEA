package com.github.LEA;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.AuthenticationException;

public class Model {

    // login information
    String username;
    String password;

    Properties properties;
    Session session;
    Transport transport;

    // SMTP
    String auth = "true";
    String host = "smtp.gmail.com";
    String port = "587";
    String enableStartTLS = "true";

    // starts the session
    public boolean startSession() {
        boolean retVal = false; // default val, not yet verified

        properties = new Properties();
        properties.put("mail.smtp.auth", auth);
        properties.put("mail.smtp.starttls.enable", enableStartTLS);
        properties.put("mail.smtp.host", host); // smtp server address
        properties.put("mail.smtp.port", port); // port for the smtp server
        properties.put("mail.smtp.ssl.trust", host);

        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // checks if account exists
        try {
            transport = session.getTransport("smtp");
            transport.connect(host, username, password);
            transport.close();
            //Authentication success
            retVal = true;
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return retVal;
    }
        // allows user to send his email
    public void sendMessage(String recipient, String subject, String msg) throws MessagingException{
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(recipient));
        message.setSubject(subject);
        message.setText(msg);
        Transport.send(message);

    }

    // login
    public void userLogin(String username, char[] password){
        this.username = username;
        // converts char array to string
        this.password = new String(password);
    }

}