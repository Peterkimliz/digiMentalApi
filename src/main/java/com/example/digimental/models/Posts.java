package com.example.digimental.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "posts")
public class Posts {
    @Id
    private String id;
    private  String message;
    private String user;
    private String category;
    private List<String> likes= new ArrayList<>();
    private Date createdAt;
    private Date updatedAt;
}
