package com.example.digimental.twilio;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Setter
@Getter
@NoArgsConstructor
@ConfigurationProperties("twilio")
@Configuration
public class TwilioConfig {
    private String accountSid;
    private String authToken;
    private String trialNumber;


}

