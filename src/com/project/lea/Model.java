package com.project.lea; /**
 * All the logic stuff goes here
 */

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Model {

    // login information
    String username;
    String password;

    Properties properties;
    Session session;

    // starts the session
    public void startSession(){
        properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com"); // smtp server address
        properties.put("mail.smtp.port", "587"); // port for the smtp server
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        session = Session.getInstance(properties,new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    // allows user to send his email
    public int sendMessage(String recipient, String subject, String msg){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(msg);
            Transport.send(message);
        }catch(MessagingException me){
            System.out.println(me.getMessage());
            return 1;
        }
        return 0;
    }

    // login
    public void userLogin(String username, char[] password){
        this.username = username;
        // converts char array to string
        this.password = new String(password);
    }

}