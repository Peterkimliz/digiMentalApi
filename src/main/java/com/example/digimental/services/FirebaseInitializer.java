package com.example.digimental.services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class FirebaseInitializer {

@Bean
    public void initializer() {

    try {
        FileInputStream serviceAccount = new FileInputStream("src/main/resources/digimhealthfirebase.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        FirebaseApp.initializeApp(options);
        System.out.println("firebase initialized");
    } catch (FileNotFoundException e) {
        System.out.println("firebase failed");
        throw new RuntimeException(e);
    } catch (IOException e) {
        System.out.println("firebase failed 1");
        throw new RuntimeException(e);
    }
}

}
