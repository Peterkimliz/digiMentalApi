package com.example.digimental.services;

import com.example.digimental.dtos.Notification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class NotificationService {

    @Autowired
    FirebaseMessaging firebaseMessaging;

    public String sendNotification(Notification note, String token) throws FirebaseMessagingException {

        com.google.firebase.messaging.Notification notification = com.google.firebase.messaging.Notification
                .builder()
                .setTitle(note.getSubject())
                .setBody(note.getContent())
                .build();

        Message message = Message
                .builder()
                .setToken(token)
                .setNotification(notification)
                .putAllData(note.getData())
                .build();

        return firebaseMessaging.send(message);
    }

}
