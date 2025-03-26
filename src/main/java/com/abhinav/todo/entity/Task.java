package com.abhinav.todo.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Getter
@Setter
public class Task {
    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String description;
    private Date date;

//    public ObjectId getId() {
//        return id;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//    public void setId(ObjectId id) {
//        this.id = id;
//    }
//    public void setDescription(String description) {
//        this.description = description;
//    }
//    public void setTitle(String title) {
//        this.title = title;
//    }
}
