package com.abhinav.todo.controller;

import com.abhinav.todo.entity.Task;
import com.abhinav.todo.entity.User;
import com.abhinav.todo.service.Service;
import com.abhinav.todo.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("task")
public class TaskUserController {

    @Autowired
    private Service taskService;  // task service

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<Task>> getUserTasks() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        User user=userService.findUserByUsername(username);
        if(user!=null){
            return new ResponseEntity<>(user.getTasks(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
//    @Transactional
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        try{
            Task savedTask=taskService.save(task);
            User savedUser=userService.findUserByUsername(username);
            if(savedUser!=null){
                savedUser.getTasks().add(task);
                userService.saveUser(savedUser);
            }
            return new ResponseEntity<>(savedTask, HttpStatus.CREATED);

        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask( @PathVariable ObjectId id, @RequestBody Task task) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        User savedUser=userService.findUserByUsername(username);
        if(savedUser!=null){
            if(!savedUser.getTasks().stream().filter(tasks -> tasks.getId().equals(id)).findFirst().isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Task updatedTask=taskService.updateById(id, task);
            userService.saveUser(savedUser);
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public boolean deleteTask(@PathVariable ObjectId id ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        User savedUser=userService.findUserByUsername(username);
        if(savedUser!=null){
            if(!savedUser.getTasks().stream().filter(task -> task.getId().equals(id)).findFirst().isPresent()){
                return false;
            }
            taskService.delete(id);
            savedUser.getTasks().remove(id);
            userService.saveUser(savedUser);
            return true;
        }
        return false;
    }
}
