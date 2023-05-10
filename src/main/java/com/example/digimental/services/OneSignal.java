package com.example.digimental.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

@Service
public class OneSignal {

    public  void  sendPushNotification(){
        try {
            HttpResponse<String> response = Unirest.post("https://onesignal.com/api/v1/notifications")
                    .header("accept", "application/json")
                    .header("Authorization", "Basic Zjk2ODFkNWMtMGFhMi00NjUyLTk0MzgtMjA1ODA5MTQ3ZTMw")
                    .header("content-type", "application/json")
                    .body("{\"included_segments\":[\"Subscribed Users\"],\"contents\":{\"en\":\"Hello this is spring application\",\"es\":\"Spanish Message\"},\"name\":\"INTERNAL_CAMPAIGN_NAME\",\"app_id\":\"6e2af418-ffae-4555-a74b-a4092781d3ca\",\"data\":{\"abc\": 123, \"foo\": \"bar\", \"event_performed\": true, \"amount\": 12.1}}")
                    .asString();
            System.out.println("response is "+response.getBody());
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }


//System.out.println(response.body());
}
