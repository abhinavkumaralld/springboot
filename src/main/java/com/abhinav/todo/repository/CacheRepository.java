package com.abhinav.todo.repository;

import com.abhinav.todo.entity.CacheEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CacheRepository extends MongoRepository<CacheEntity, String> {
}
