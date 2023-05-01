package com.example.digimental.services;

import com.example.digimental.dtos.ReviewDto;
import com.example.digimental.exceptions.FoundException;
import com.example.digimental.exceptions.NotFoundException;
import com.example.digimental.models.Reviews;
import com.example.digimental.models.User;
import com.example.digimental.repository.ReviewRepository;
import com.example.digimental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;

    public Reviews createReview(ReviewDto reviewDto, String doctorId) {
        Optional<User> user = userRepository.findById(reviewDto.getId());
        Optional<User> doctor = userRepository.findById(doctorId);
        if (user.isEmpty() && doctor.isEmpty()) {
            throw new NotFoundException("user not found");
        }

        Optional<Reviews> foundReview = reviewRepository.findByUserAndReviewer(reviewDto.getId(), user.get());
        if (foundReview.isPresent()) {
            throw new FoundException("review already exists");
        }
        Reviews reviews = new Reviews();
        reviews.setReviewer(user.get());
        reviews.setCreatedAt(new Date(System.currentTimeMillis()));
        reviews.setMessage(reviewDto.getMessage());
        reviews.setUser(doctorId);
        reviews.setRating(Integer.parseInt(reviewDto.getRating()));
        return reviewRepository.save(reviews);
    }
    public List<Reviews>getReviewsByUserId(String userId,int pageNumber){
       Pageable page= PageRequest.of(pageNumber,15).withSort(Sort.Direction.DESC,"createdAt");
       Page<Reviews> reviews=reviewRepository.findByUser(userId,page);

        if (reviews.toList().size()==0){
            return  new ArrayList<>();
        }
        return  reviews.toList();
    }

}
