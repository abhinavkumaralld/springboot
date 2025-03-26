package com.abhinav.todo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    public boolean sendEmail(String to, String subject, String body) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
            return true;
        }catch(Exception e){
            log.error("Got Error while sending mail "+e.getMessage());
        }
        return false;
    }

//    @Scheduled(cron = "*/30 * * * * *")
    public boolean sendScheduledEmail() {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("abhinavkr2272@gmail.com");
            message.setSubject("scheduled email");
            message.setText("scheduled email ka body");
            System.out.println("scheduled email ka printitng");
            mailSender.send(message);
            return true;
        }catch(Exception e){
            log.error("Got Error while sending mail "+e.getMessage());
        }
        return false;
    }
}
