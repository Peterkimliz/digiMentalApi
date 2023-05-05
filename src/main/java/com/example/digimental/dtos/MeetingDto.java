package com.example.digimental.dtos;

import com.example.digimental.models.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeetingDto {
    @NotBlank(message = "please enter doctor")
    private String doctor;
    @NotBlank(message = "please enter type")
    private String type;
    @NotBlank(message = "please enter meetingDay")
    private String meetingDay;
    @NotBlank(message = "please enter duration")
    private String duration;
    @NotBlank(message = "please enter start time")
    private String startTime;
    @NotBlank(message = "please enter user")
    private String user;
    private String status;
    private List<User> userList;
    private List<User>  raisedHands;
    private String title ;
    private int charges;


}
