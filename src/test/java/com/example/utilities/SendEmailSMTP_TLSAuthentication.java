package com.example.utilities;

import com.example.base.TestBase;
import org.apache.log4j.PropertyConfigurator;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailSMTP_TLSAuthentication extends TestBase {

    private String FROM_EMAIL = "makarov@smartproject.ua";
    private String FROM_PASSWORD = "chimaira";
    //private String TO_EMAIL = "support@socialgames.bz";
    private String TO_EMAIL = "disappear.arnie@gmail.com";
    private String JenkinsHTMLReport = "http://localhost:8080/job/DataDrivenFramework/HTML_20Report/";

    public void sendEmail() {
        PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties");
        // Create a Properties object to contain connection configuration information.
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        log.info("Created a Properties object.");

        // Create a Session object to represent a mail session with the specified properties.
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, FROM_PASSWORD);
            }
        });
        log.info("Created a Session object.");

        // Creating a Message object to set the email content.
        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, TO_EMAIL);
            message.setSubject("Send Email SMTP Report: " + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()));
            message.setSentDate(new Date());
            message.setText("Jenkins HTML Report: ");
            log.info("Message Generated.");

            Transport.send(message);
            log.info("Success. Message sent successfully.");
        } catch (MessagingException mex) {
            mex.printStackTrace();
            log.info("Failure. Message has not sent.");
        }
    }
}