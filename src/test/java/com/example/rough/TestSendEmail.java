package com.example.rough;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class TestSendEmail {
    public static void main(String[] args) {
        //Setting up configurations for the email connection to the Google SMTP server using TLS
        Properties props = new Properties();
        props.put("mail.smtp.host", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        //Establishing a session with required user details
        Session session = Session.getInstance(props, new Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("disappear.arnie@gmail.com", "887456113chimaira");
            }
        });
        try {
            //Creating a Message object to set the email content
            MimeMessage msg = new MimeMessage(session);
            //Storing the comma seperated values to email addresses
            msg.setFrom("disappear.arnie@gmail.com");
            String to = "support@socialgames.bz";
            /*Parsing the String with defualt delimiter as a comma by marking the boolean as true and storing the email
            addresses in an array of InternetAddress objects*/
            InternetAddress[] address = InternetAddress.parse(to, true);
            //Setting the recepients from the address variable
            msg.setRecipients(Message.RecipientType.TO, address);
            String timeStamp = new SimpleDateFormat("yyyy-mm-dd_hh-mm-ss").format(new Date());
            msg.setSubject("Sample Mail : " + timeStamp);
            msg.setSentDate(new Date());
            msg.setText("Sampel System Generated mail");
            msg.setHeader("XPriority", "1");
            Transport.send(msg);
            System.out.println("Mail has been sent successfully");
        } catch (MessagingException mex) {
            System.out.println("Unable to send an email" + mex);
        }
    }
}
