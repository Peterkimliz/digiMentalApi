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
@RequestMapping("api/patient/")
public class UserController {
    @Autowired
   private UserService patientService;

    @PostMapping("create")
    public ResponseEntity<User> createPatient(@RequestBody @Validated UserDto patientDto) {
        return new ResponseEntity<>(patientService.createPatient(patientDto), HttpStatus.CREATED);
    }

    @GetMapping("all")
    public ResponseEntity<List<User>> fetchPaginatedPatients(@RequestParam(required = true) String pageNumber) {
        List<User> patients = patientService.fetchPaginatedPatients(pageNumber);
        return new ResponseEntity<>(patients, patients.size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getPatientById(@PathVariable("id") String id) {
        return new ResponseEntity<>(patientService.fetchPatientById(id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePatientById(@PathVariable("id") String id) {
        patientService.deletePatientById(id);
        return new ResponseEntity<>("patient deleted", HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updatePatientById(@PathVariable("id") String id, @RequestBody User patient) {

        return new ResponseEntity<>(patientService.updatePatientById(id, patient), HttpStatus.OK);
    }
}
