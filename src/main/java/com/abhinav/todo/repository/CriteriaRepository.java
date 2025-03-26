package com.abhinav.todo.repository;

import com.abhinav.todo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CriteriaRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public ResponseEntity<List<User>> getUserUsingCriteriaApi() {
        Query query = new Query();

        query.addCriteria(Criteria.where("emailId").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"));

        Criteria criteria = new Criteria();
        query.addCriteria(criteria.andOperator(
                Criteria.where("emailId").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"),
                Criteria.where("sentimentAnalysis").is(true)
        ));
        List<User>  userList = mongoTemplate.find(query, User.class);
         return new ResponseEntity<>(userList, HttpStatus.OK);    }
}
