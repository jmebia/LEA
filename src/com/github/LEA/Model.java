package com.github.LEA;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Model {

    /* =================================== SESSION AND AUTH SECTION ========================================== */
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

    // login
    public void userLogin(String username, char[] password){
        this.username = username;
        // converts char array to string
        this.password = new String(password);
    }

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
    /* ======================================================================================================= */
    /* ===================================== MESSAGING SECTION =============================================== */

    // container for attachments
    List<File> attachments = new ArrayList<File>();

    // add attachment
    public boolean addFile(String title, String fileSource){
        boolean isUnique = true;
        // checks whether the file name given is unique
        for(File file : attachments) {
            if(file.title == title){
                isUnique = false;
                break;
            }
        }
        // if file name is unique then proceed
        if(isUnique == true){
            attachments.add(new File(title, fileSource));
        }

        return isUnique;
    }

    // remove attachmnet
    public boolean removeFile(String title){
        boolean isRemoved = false;

        for(File file : attachments){
            if(file.title == title) {
                attachments.remove(file);
                isRemoved = true;
                System.out.println("File "+title+" is removed.");
                break; // stops loop after finding the file
            }
        }

        return isRemoved;
    }

    // allows user to send his email
    public void sendMessage(String recipient, String subject, String msg){
        try {
            // starts a mime message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient));
            message.setSubject(subject);

            // will contain everything on the email
            Multipart multiPart = new MimeMultipart();

            // creates a body part
            BodyPart messageBody = new MimeBodyPart();
            messageBody.setText(msg); // sets text message
            multiPart.addBodyPart(messageBody);

            // if a file attachment exists in the list, then proceed
            if (!attachments.isEmpty()) {
                System.out.println("Attachment(s) found!");
                DataSource source;
                // for every file in the container, do this
                for (File file: attachments) {
                    messageBody = new MimeBodyPart();
                    source = new FileDataSource(file.fileSource); // gets file source or path
                    messageBody.setDataHandler(new DataHandler(source));
                    messageBody.setFileName(file.title); // file name
                    multiPart.addBodyPart(messageBody);
                }
            } else {
                System.out.println("No attachments!");
            }
            message.setContent(multiPart); // puts everything to the email message
            Transport.send(message); // sends message
        } catch (MessagingException me) {
            System.out.println(me.getMessage());
        }
    }

    // Inner class
    // class for file attachments
    private class File {
        String title;
        String fileSource;

        public File(String title, String fileSource){
            this.title = title;
            this.fileSource = fileSource;
        }
    }
}