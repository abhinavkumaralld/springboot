package com.abhinav.todo.service;

import com.abhinav.todo.entity.User;
import com.abhinav.todo.entity.WeatherResponse;
import com.abhinav.todo.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService  {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserByUsername(String username ) {
        return userRepository.findByUsername(username);
    }
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public boolean updateById(ObjectId id) {
        userRepository.deleteById(id);
        return true;
    }

    public boolean deleteById(ObjectId id) {
        userRepository.deleteById(id);
        return true;
    }

}
