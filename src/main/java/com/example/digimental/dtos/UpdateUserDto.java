package com.example.digimental.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {
    private  Boolean mailSend;
    private String username;
    private String phone;
    private String dob;
    private String gender;
    private String age;
    private String profileImage;
    private String bio;
    private String country;
    private String county;
    private String subcounty;
    private String yearsOfExperience;
    private String consultationFee;
    private String certurl;
    private List<String> category=new ArrayList<>();
    private  List<String>workingDays=new ArrayList<>();

}
