package com.abhinav.todo.repository;

import com.abhinav.todo.entity.Task;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface Repository extends MongoRepository<Task, ObjectId> {
}
