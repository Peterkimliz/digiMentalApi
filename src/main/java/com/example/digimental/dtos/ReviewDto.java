package com.example.digimental.dtos;

import jakarta.validation.constraints.NotBlank;

public class ReviewDto {
    @NotBlank(message = "Please enter the message")
    private String message;
    @NotBlank(message = "please enter rating")
    private String rating;
}
