package com.example.digimental.repository;

import com.example.digimental.models.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByEmail(String email);
    Page<User> findByCategoryAndTypeAndIsVerified(List<String> category, String type,boolean isVerified,Pageable pageable);
    Page<User> findByTypeAndIsVerified(String doctor, boolean isVerified, Pageable paging);
}
