package com.example.utilities;

import com.example.base.TestBase;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailSMTP_TLSAuthentication extends TestBase {

    private String FROM_EMAIL = config.getProperty("from");
    private String FROM_PASSWORD = config.getProperty("password");
    private String TO_EMAIL = config.getProperty("to");
    private static final String URL_JENKINS_EXTENTREPORT = "http://localhost:8080/job/DataDrivenFramework/Extent_20Report_20HTML/";

    public void sendEmail() {
        // Create a Properties object to contain connection configuration information.
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        log.info("Create a Properties object.");

        // Create a Session object to represent a mail session with the specified properties.
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, FROM_PASSWORD);
            }
        });
        log.info("Create a Session object.");

        // Creating a Message object to set the email content.
        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, TO_EMAIL);
            message.setSubject("Send Email SMTP Report: " + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()));
            message.setSentDate(new Date());
            message.setText("Jenkins HTML Report: " + URL_JENKINS_EXTENTREPORT);
            log.info("Message Generated.");

            Transport.send(message);
            log.info("Success. Message sent successfully.");
        } catch (MessagingException mex) {
            log.info("Failure. Message has't sent.");
            mex.printStackTrace();
        }
    }
}