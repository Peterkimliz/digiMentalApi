package com.example.digimental.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "categories")
public class Categories {
    @Id
    private  String id;
    private String name;
    private Date createdAt;
    private Date updatedAt;
}
