package com.example.digimental.services;

import com.example.digimental.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostsService {
    @Autowired
    PostRepository postRepository;
}
