package com.marcellusinfotech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("your-email@gmail.com"); // Sender's email address
        message.setTo(to);                       // Receiver's email address
        message.setSubject(subject);             // Email subject
        message.setText(text);                   // Email body
        mailSender.send(message);                // Sending the email
    }
}
