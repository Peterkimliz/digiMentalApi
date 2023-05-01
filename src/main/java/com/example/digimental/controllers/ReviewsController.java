package com.example.digimental.controllers;

import com.example.digimental.dtos.ReviewDto;
import com.example.digimental.models.Reviews;
import com.example.digimental.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reviews")
public class ReviewsController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<Reviews> createReview(@RequestBody ReviewDto reviewDto, @PathVariable("userId") String userId) {

        return new ResponseEntity<>(reviewService.createReview(reviewDto,userId), HttpStatus.CREATED);
    }
    @GetMapping ("/all/{userId}")
    public ResponseEntity<List<Reviews>> getReviews(@PathVariable("userId") String userId,@RequestParam(required = true) int pageNumber) {
        List<Reviews> reviews=reviewService.getReviewsByUserId(userId,pageNumber);
        return new ResponseEntity<>(reviews,reviews.size()==0?HttpStatus.NOT_FOUND: HttpStatus.CREATED);
    }


}
