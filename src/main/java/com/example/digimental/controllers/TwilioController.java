package com.example.digimental.controllers;

import com.example.digimental.twilio.SmsRequest;
import com.example.digimental.twilio.TwilioSmsSender;
import com.twilio.twiml.voice.Sms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/twilio/")
public class TwilioController {
    @Autowired
    TwilioSmsSender twilioSmsSender;

    @Bean
    public void sendSms() {

        twilioSmsSender.sendSms(new SmsRequest("+254795388105","hello"));
    }

}
