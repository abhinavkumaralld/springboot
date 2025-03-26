package com.abhinav.todo.controller;

import com.abhinav.todo.entity.Task;
import com.abhinav.todo.entity.User;
import com.abhinav.todo.service.Service;
import com.abhinav.todo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {
    @Autowired
    private UserService userService;

    public static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(new ArrayList<>());
            userService.saveUser(user);
            return new ResponseEntity<>(user,HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("error log by me "+e.getMessage());
            log.info("error log by me "+e.getMessage());
            log.trace("error log by me "+e.getMessage());
            log.warn("error log by me "+e.getMessage());
            log.debug("error log by me "+e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
