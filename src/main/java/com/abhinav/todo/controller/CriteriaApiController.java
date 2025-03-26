package com.abhinav.todo.controller;

import com.abhinav.todo.entity.User;
import com.abhinav.todo.repository.CriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("query")
public class CriteriaApiController {


    @Autowired
    CriteriaRepository criteriaRepository;

    @GetMapping()
    public ResponseEntity<List<User>> getUserFromCriteriaApi() {
        return criteriaRepository.getUserUsingCriteriaApi();
    }

}
