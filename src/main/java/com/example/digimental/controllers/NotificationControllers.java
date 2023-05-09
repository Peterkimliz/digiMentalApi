package com.example.digimental.controllers;

import com.example.digimental.dtos.Notification;
import com.example.digimental.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/notification")
public class NotificationControllers {
    @Autowired
    NotificationService notificationService;
    @PostMapping("/single")
    public ResponseEntity<String> sendNotificationToSingleClient(@RequestBody String token){
        Notification notification=new Notification();
        notification.setContent("hello this is single tokens");
        notification.setSubject("hello this is single tokens");
        notificationService.sendNotification(notification,token);
        return new ResponseEntity<>("notification sent", HttpStatus.OK);
    }
    @PostMapping("/multiple")
    public ResponseEntity<String> sendNotificationToMultipleClienst(@RequestBody List<String> tokens){
        Notification notification=new Notification();
        notification.setContent("hello this is multiple tokens");
        notification.setSubject("hello this is multiple tokens");
        notificationService.sendMultipleNotification(notification,tokens);
        return new ResponseEntity<>("notifications sent", HttpStatus.OK);
    }
}
