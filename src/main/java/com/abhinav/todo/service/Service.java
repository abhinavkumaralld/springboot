package com.abhinav.todo.service;

import com.abhinav.todo.entity.Task;
import com.abhinav.todo.repository.Repository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Date;
import java.util.List;
@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private Repository repository;

    public Task save(Task task) {
        task.setDate(new Date());
        return repository.save(task);
    }
    public List<Task> getAll() {
        return repository.findAll();
    }
    public boolean delete(ObjectId id) {
        if(repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
    public Task updateById(ObjectId id,Task task){
        if(repository.findById(id).isPresent()) {
            Task task1 = repository.findById(id).get();
            task1.setTitle(task.getTitle());
            task1.setDescription(task.getDescription());
            return repository.save(task1);
        }
        return null;

    }
}
