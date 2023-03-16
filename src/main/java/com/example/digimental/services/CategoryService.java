package com.example.digimental.services;

import com.example.digimental.dtos.CategoryDto;
import com.example.digimental.exceptions.FoundException;
import com.example.digimental.exceptions.NotFoundException;
import com.example.digimental.models.Categories;
import com.example.digimental.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Categories createCategory(CategoryDto categoryDto) {
        Optional<Categories> foundCategory = categoryRepository.findByName(categoryDto.getName().toLowerCase().trim());
        if (foundCategory.isPresent()) {
            throw new FoundException("category with that name already exist");
        } else {
            Categories categories = new Categories();
            categories.setName(categoryDto.getName().toLowerCase().trim());
            categories.setCreatedAt(new Date(System.currentTimeMillis()));
            categories.setUpdatedAt(new Date(System.currentTimeMillis()));
            return categoryRepository.save(categories);
        }
    }

    public Categories getCategoryById(String id) {
        Optional<Categories> categories = categoryRepository.findById(id);
        if (categories.isEmpty()) {
            throw new NotFoundException("category doesn't exist");
        }

        return categories.get();

    }
    public void deleteCategoryById(String id) {
        Optional<Categories> categories = categoryRepository.findById(id);
        if (categories.isEmpty()) {
            throw new NotFoundException("category doesn't exist");
        } else {
            categoryRepository.deleteById(id);
        }
    }
    public Categories updateCategoryById(String id,CategoryDto categoryDto) {
        Optional<Categories> categories = categoryRepository.findById(id);
        if (categories.isEmpty()) {
            throw new NotFoundException("category doesn't exist");
        } else {
            Optional<Categories> foundCategory = categoryRepository.findByName(categoryDto.getName().toLowerCase().trim());
            if (foundCategory.isPresent()){
                throw new NotFoundException("category already exist");
            }
            Categories savedCategory=foundCategory.get();
            savedCategory.setUpdatedAt(new Date(System.currentTimeMillis()));
            savedCategory.setName(categoryDto.getName().toLowerCase().trim());
           return categoryRepository.save(savedCategory);
        }
    }

    public List<Categories>getAllCategories(){
        List<Categories> categories=categoryRepository.findAll(Sort.by(Sort.Direction.DESC,"createdAt"));
        if (categories.size() >0){
            return categories;
        }else {
            return  new ArrayList<>();
        }
    }
}
