package com.example.digimental.repository;

import com.example.digimental.models.Categories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends MongoRepository<Categories,String>{
    Optional<Categories> findByName(String name);
}
