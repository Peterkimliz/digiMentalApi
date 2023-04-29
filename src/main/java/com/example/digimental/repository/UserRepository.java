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
    Page<User> findByType(String doctor, Pageable paging);
    Page<User> findByCategoryAndType(List<String> category, String type,Pageable pageable);
}
