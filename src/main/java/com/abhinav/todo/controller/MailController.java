package com.abhinav.todo.controller;

import com.abhinav.todo.entity.Email;
import com.abhinav.todo.entity.User;
import com.abhinav.todo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("email")
public class MailController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public boolean sendEmail(@RequestBody Email email) {
        return emailService.sendEmail(email.getTo(),email.getSubject(),email.getBody());
    }
}
