package com.abhinav.todo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "config")
public class CacheEntity {
    private String key;
    private String value;
}
