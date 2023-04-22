package com.example.digimental.controllers;

import com.example.digimental.dtos.LoginDto;
import com.example.digimental.dtos.LoginResponse;
import com.example.digimental.dtos.UserDto;
import com.example.digimental.models.User;
import com.example.digimental.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/authentication/")

public class AuthController {
    @Autowired
    private UserService userService;
    @PostMapping("create")
    public ResponseEntity<LoginResponse> createuser(@RequestBody  @Validated  UserDto userDto) {
        System.out.println("called");
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }
    @PostMapping("signin")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody @Validated LoginDto loginDto){
        return new ResponseEntity<LoginResponse>(userService.loginUser(loginDto),HttpStatus.OK);
    }
}
