package com.abhinav.todo.entity;

import com.mongodb.lang.NonNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
@NoArgsConstructor
public class User {

    @Id
    ObjectId id;
    @Indexed(unique = true)
    @NonNull
    String username;
    @NonNull
    String password;
    @DBRef
    List<Task> tasks=new ArrayList<>();
    private List<String> roles=new ArrayList<>();

    private String emailId;
    private boolean sentimentAnalysis ;
}
