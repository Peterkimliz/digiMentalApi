package com.example.digimental.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReviewDto {
    @NotBlank(message = "Please enter the message")
    private String message;
    @NotBlank(message = "please enter rating")
    private String rating;
    @NotBlank(message = "please enter reviewer id")
    private String id;
}
