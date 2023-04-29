package com.example.digimental.controllers;

import com.example.digimental.dtos.UpdateUserDto;
import com.example.digimental.models.User;
import com.example.digimental.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("all")
    public ResponseEntity<List<User>> fetchPaginatedUsers(@RequestParam(required = true) String pageNumber) {
        List<User> users = userService.fetchPaginatedUsers(pageNumber);
        return new ResponseEntity<>(users, users.size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
        return new ResponseEntity<>(userService.fetchUserById(id), HttpStatus.OK);
    }

    @GetMapping("doctors/search")
    public ResponseEntity<List<User>> searchByCategory(@RequestParam(required = true) String category, @RequestParam(required = true) int pageNumber, @RequestParam(required = false) String name) {
        List<User> users = userService.searchusersByCategory(category, pageNumber, name);
        return new ResponseEntity<>(users, users.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") String id) {
        userService.deleteuserById(id);
        return new ResponseEntity<>("user deleted", HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUserById(@PathVariable("id") String id, @RequestBody UpdateUserDto updateUserDto) {

        return new ResponseEntity<>(userService.updateUserById(id, updateUserDto), HttpStatus.OK);
    }

    @PutMapping("doctors/update/{id}")
    public ResponseEntity<User> uploadDoctorDetails(@PathVariable("id") String id, @RequestBody UpdateUserDto updateUserDto) {

        return new ResponseEntity<>(userService.uploadDoctorDetailsUserById(id, updateUserDto), HttpStatus.OK);
    }

    @PutMapping("doctors/verify/{id}")
    public ResponseEntity<String> verifyDoctorDetails(@PathVariable("id") String id) {
        userService.verifyDoctor(id);
        return new ResponseEntity<>("user verified", HttpStatus.OK);
    }
}
