package com.example.digimental.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "patient")
public class User {
    @Id
    private String id;
    private String email;
    private String username;
    private String phone;
    @JsonIgnore
    private String password;
    private String dob;
    private String gender;
    private String type;
    private boolean isVerified = false;
    private boolean isPhoneVerified = false;
    private Date createdAt;
    private Date updatedAt;


}
