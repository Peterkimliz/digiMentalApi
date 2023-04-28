package com.example.digimental.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "reviews")
public class Reviews {
    @Id
    private  String id;
    private String message;
    private User reviewer;
    private String user;
    private int rating;
    private Date createdAt;
    private Date updatedAt;
}
