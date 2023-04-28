package com.example.digimental.dtos;

import lombok.Data;

import java.util.Map;
@Data
public class Notification {
    private String subject;
    private String content;
    private Map<String, String> data;
    private String image;
}
