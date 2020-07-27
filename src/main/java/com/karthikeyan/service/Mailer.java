package com.karthikeyan.service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author Karthikeyan on 25/07/20
 */

class Mailer {
    public void sendMail(String content, String[] too) {
        String from = "karthikeyan100308@gmail.com";
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "Credit@123");
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));

            InternetAddress[] addresses = new InternetAddress[too.length];
            for (int i = 0; i < too.length; i++) {
                addresses[i] = new InternetAddress(too[i]);
            }
            message.addRecipients(Message.RecipientType.TO, addresses);
            message.setSubject("Karthikeyan Mailer");
            message.setContent(content, "text/html; charset=utf-8");
            System.out.println("sending...");
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}

