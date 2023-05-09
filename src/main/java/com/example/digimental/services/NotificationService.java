package com.example.digimental.services;

import com.example.digimental.dtos.Notification;
import com.google.firebase.messaging.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class NotificationService {
    @Autowired
    FirebaseMessaging firebaseMessaging;

    public void sendNotification(Notification note, String token) {

        try {
//            com.google.firebase.messaging.Notification notification = com.google.firebase.messaging.Notification
//                    .builder()
//                    .setTitle(note.getSubject())
//                    .setBody(note.getContent())
//                    .build();

            Message message = Message
                    .builder()
                    .setToken(token)
//                    .setNotification(notification)
//                    .putAllData(note.getData())
                    .putData("content",note.getContent())
                    .build();
            System. out. println(token);
            System. out. println( firebaseMessaging.send(message));

            firebaseMessaging.send(message);
        } catch (FirebaseMessagingException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendMultipleNotification(Notification note, List<String> token) {

        try {
            MulticastMessage message = MulticastMessage
                    .builder()
                    .addAllTokens(token)
//                    .setNotification(notification)
//                    .putAllData(note.getData())
                    .putData("content",note.getContent())
                    .build();
            System. out. println(token);
          BatchResponse batchResponse= firebaseMessaging.sendMulticast(message);
            System.out.println("batch response is "+batchResponse);
        } catch (FirebaseMessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
