package com.smart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public boolean sendEmail(String toEmail,
                          String subject,
                          String body){
        boolean flag=false;
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("kartikdummy.ac@gmail.com");
        msg.setTo(toEmail);
        msg.setText(body);
//        msg.se
        msg.setSubject(subject);

        mailSender.send(msg);
        flag=true;
        System.out.println("Mail sent Successfully");
        return flag;
    }
}
