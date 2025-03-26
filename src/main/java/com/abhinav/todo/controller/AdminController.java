package com.abhinav.todo.controller;

import com.abhinav.todo.entity.User;
import com.abhinav.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private UserService userService;

    public static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<User>userList=userService.getAllUsers();
        if(!userList.isEmpty()){
            return new ResponseEntity<>(userList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<User> saveAdminUser(@RequestBody User user) {
        user.getRoles().add("ADMIN");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser=userService.saveUser(user);
        if(savedUser!=null){
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
