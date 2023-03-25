package com.example.digimental.repository;

import com.example.digimental.models.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AdminRepository extends MongoRepository<Admin,String>{
    Optional<Admin> findByEmail(String email);

}
