package com.example.digimental.repository;

import com.example.digimental.models.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AdminRepository extends MongoRepository<Admin,String>{
    Optional<Admin> findByEmail(String email);

}