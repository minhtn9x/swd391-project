package com.dma.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtils {

    private static final String EMAIL = "swp391.dma@gmail.com";
    private static final String PASSWORD = "armlbswhuxemtfqc";

    public static void sendOTPEmail(Session session, String toEmail, String subject, String body, int otpValue) throws MessagingException, UnsupportedEncodingException {
        MimeMessage msg = new MimeMessage(session);
        // set message's headers
        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
        msg.addHeader("format", "flowed");
        msg.addHeader("Content-Transfer-Encoding", "8bit");

        msg.setFrom(new InternetAddress(toEmail));

        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

        msg.setSubject(subject, "UTF-8");

        msg.setContent(body, "text/html");

        msg.setText("Your OTP is: " + otpValue);

        msg.setSentDate(new Date());

        Transport.send(msg);

        System.out.println("Email Sent To " + toEmail + " Successfully!\n");
    }

    public static Session createEmailSession() throws UnsupportedEncodingException, MessagingException {
        System.out.println("SSLEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL, PASSWORD);
            }
        };

        Session session = Session.getDefaultInstance(props, auth);
        System.out.println("Session created");
        if (session != null) {
            return session;
        }
        return null;
    }
}
