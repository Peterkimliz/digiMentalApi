package com.example.digimental.controllers;

import com.example.digimental.dtos.UserDto;
import com.example.digimental.models.User;
import com.example.digimental.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user/")
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

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") String id) {
        userService.deleteuserById(id);
        return new ResponseEntity<>("user deleted", HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUserById(@PathVariable("id") String id, @RequestBody User patient) {

        return new ResponseEntity<>(userService.updateUserById(id, patient), HttpStatus.OK);
    }
}
