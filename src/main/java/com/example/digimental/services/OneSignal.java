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
                    .header("Authorization", "Basic NTY0MTJhOGMtYTE1OS00ZmZlLTk0NjAtOTg1ZjU4NzdkY2U2")
                    .header("content-type", "application/json")
                    .body("{\"included_segments\":[\"Subscribed Users\"],\"contents\":{\"en\":\"Hello this is spring application\",\"es\":\"Spanish Message\"},\"name\":\"INTERNAL_CAMPAIGN_NAME\",\"app_id\":\"a10568db-f14b-4823-9142-73f54651b395\",\"data\":{\"abc\": 123, \"foo\": \"bar\", \"event_performed\": true, \"amount\": 12.1}}")
                    .asString();
            System.out.println("response is "+response.getBody());
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }


//System.out.println(response.body());
}
