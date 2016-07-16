package com.project.lea; /**
 * All the logic stuff goes here
 */

import com.sun.javaws.exceptions.MissingFieldException;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Model {

    // login information
    String username;
    String password;

    Properties properties;
    Session session;

    // starts the session
    public boolean startSession(){
        boolean retAuth = true;
        Transport transport;
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
        try {
            transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", username, password);
            transport.close();
            //Authentication success
        } catch (AuthenticationFailedException e) {
            //Authentication failed. Handle this here.
            System.out.println("Authentication Exception");
            retAuth = false;
        } catch (MessagingException me){

        }
        return retAuth;
    }

    // allows user to send his email
    public boolean sendMessage(String recipient, String subject, String msg){
        boolean check = false;
        try {
            InternetAddress emailAdd = new InternetAddress(recipient);
            emailAdd.validate();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(msg);
            Transport.send(message);
            check = true;
        }catch(MessagingException me) {
            System.out.println(me.getMessage());
        }
        return check;
    }

    // login
    public boolean userLogin(String username, char[] password){
        boolean check = true;
        if(username.contains("@gmail.com")) {
            this.username = username;
            // converts char array to string
            this.password = new String(password);
        }
        else
            check = false;
        return check;
    }

}
