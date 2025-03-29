package com.abhinav.todo.controller;

import com.abhinav.todo.entity.User;
import com.abhinav.todo.entity.WeatherResponse;
import com.abhinav.todo.service.UserService;
import com.abhinav.todo.service.UserServiceForPasswordAuth;
import com.abhinav.todo.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WeatherService weatherService;

    public static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping
    public ResponseEntity<User> getUsers() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User userInDb=userService.findUserByUsername(auth.getName());
        return new ResponseEntity<>(userInDb, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User userInDb=userService.findUserByUsername(user.getUsername());
        if(userInDb!=null){
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.saveUser(userInDb);
            return new ResponseEntity<>(userInDb,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> updateUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName()+"   "+auth);
        User userInDb=userService.findUserByUsername(auth.getName());
        if(userInDb!=null){
            userService.deleteById(userInDb.getId());
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
    }

    @GetMapping("weather")
    public ResponseEntity<String> getWeather() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username= auth.getName();
        WeatherResponse weatherResponse = weatherService.getWeather("mumbai");
        System.out.println(username+" weather=  "+weatherResponse.getCurrent().observationTime);
        return new ResponseEntity<>("Hi "+username+" today's temperature is " +weatherResponse.getCurrent().temperature,HttpStatus.OK);
    }
}
