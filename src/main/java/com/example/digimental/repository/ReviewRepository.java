package com.example.digimental.repository;

import com.example.digimental.models.Reviews;
import com.example.digimental.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends MongoRepository<Reviews,String> {
    Optional<Reviews>findByUserAndReviewer(String user, User reviewer);

}
