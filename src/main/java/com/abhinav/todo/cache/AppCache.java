package com.abhinav.todo.cache;

import com.abhinav.todo.entity.CacheEntity;
import com.abhinav.todo.repository.CacheRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public Map<String,String> cache;

    @Autowired
    public CacheRepository cacheRepository;

    public enum appKeys{
        API_REQUEST;
    }

    @PostConstruct
    public void init() {
        cache = new HashMap<>();
        List<CacheEntity> entities = cacheRepository.findAll();
        for (CacheEntity entity : entities) {
            cache.put(entity.getKey(),entity.getValue());
        }
    }
}
