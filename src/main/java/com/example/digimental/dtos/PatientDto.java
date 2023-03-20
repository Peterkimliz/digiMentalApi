package com.example.digimental.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PatientDto {

    @NotBlank(message = "Please enter your email")
    private String email;
    @NotBlank(message = "Please enter your phone")
    private String phone;
    @NotBlank(message = "Please enter your username")
    private String username;
    @NotBlank(message = "Please enter your password")
    @JsonIgnore
    private String password;
}
