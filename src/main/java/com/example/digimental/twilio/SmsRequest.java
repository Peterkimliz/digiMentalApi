package com.example.digimental.twilio;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SmsRequest {
    @NotBlank(message = "number required")
    private final String phoneNumber;
    @NotBlank(message = "message required")
    private final String message;
}
