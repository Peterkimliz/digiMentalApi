package com.example.digimental.controllers;

import com.example.digimental.dtos.CategoryDto;
import com.example.digimental.models.Categories;
import com.example.digimental.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories/")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("create")
    public ResponseEntity<Categories> createCategory(@RequestBody @Validated CategoryDto categoryDto) {
        return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

    @GetMapping("all")
    public ResponseEntity<List<Categories>> createCategory() {
        List<Categories> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, categories.size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Categories> getCategoryById(@PathVariable("id") String id) {
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable("id") String id) {
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>("category deleted", HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Categories> updateCategoryById(@PathVariable("id") String id, @RequestBody @Validated CategoryDto categoryDto) {

        return new ResponseEntity<>(categoryService.updateCategoryById(id, categoryDto), HttpStatus.OK);
    }

}
