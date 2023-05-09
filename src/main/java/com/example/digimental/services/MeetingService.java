package com.example.digimental.services;

import com.example.digimental.dtos.MeetingDto;
import com.example.digimental.dtos.Notification;
import com.example.digimental.exceptions.NotFoundException;
import com.example.digimental.models.Meeting;
import com.example.digimental.models.User;
import com.example.digimental.repository.MeetingRepository;
import com.example.digimental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class MeetingService {

    @Autowired
    private  MeetingRepository meetingRepository;
    @Autowired
    UserRepository userRepository;



    public Meeting createMeeting(MeetingDto meetingDto){
        Optional<User> user=userRepository.findById(meetingDto.getUser());
        Optional<User> doc=userRepository.findById(meetingDto.getDoctor());

        if (user.isEmpty() || doc.isEmpty()){
            throw  new NotFoundException("user not found");
        }

        Meeting meeting=new Meeting();
        meeting.setCreatedAt(new Date(System.currentTimeMillis()));
        meeting.setUpdatedAt(new Date(System.currentTimeMillis()));
        meeting.setMeetingDay(meetingDto.getMeetingDay());
        meeting.setCharges(meeting.getCharges());
        meeting.setUser(meetingDto.getUser());
        meeting.setDoctor(doc.get());
        meeting.setDuration(meeting.getDuration());
        meeting.setType(meetingDto.getType());
        meeting.setTitle(meetingDto.getTitle());
        meeting.setStartTime(meetingDto.getStartTime());
        meeting.setStatus(meetingDto.getStatus());
        Notification notification=new Notification();
        return  meetingRepository.save(meeting);
    }


    public Meeting getMeetingByUserId(String userId,String type,int pageNumber ) {


        return new Meeting();
    }
}
