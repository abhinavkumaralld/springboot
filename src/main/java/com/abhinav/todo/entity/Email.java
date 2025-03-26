package com.abhinav.todo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
public class Email {
    private String to;
    private String subject;
    private String body;
    private String cc;
    private String bcc;
}
