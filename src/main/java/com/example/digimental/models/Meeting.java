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
@Document(collection = "meeting")
public class Meeting {
    @Id
    private String id;
    private String status;
    private  User doctor;
    private String type;
    private String meetingDay;
    private int duration;
    private String startTime;
    private String user;
    private List<User> userList=new ArrayList<>();
    private List<User>  raisedHands =new ArrayList<>();
   private String title = "";
   private int charges=0;
   private Date createdAt;
   private Date updatedAt;
}
