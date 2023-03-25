package com.example.digimental.services;

import com.example.digimental.dtos.UserDto;
import com.example.digimental.exceptions.FoundException;
import com.example.digimental.exceptions.NotFoundException;
import com.example.digimental.models.User;
import com.example.digimental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService{
    @Autowired
    private UserRepository patientRepository;
    @Autowired
   private PasswordEncoder passwordEncoder;


    public User createPatient(UserDto patientDto) {
        Optional<User> foundPatient = patientRepository.findByEmail(patientDto.getEmail());
        if (foundPatient.isPresent()) {
            throw new FoundException("patient with provided email already exists");
        }
        User patient = new User();
        patient.setCreatedAt(new Date(System.currentTimeMillis()));
        patient.setUpdatedAt(new Date(System.currentTimeMillis()));
        patient.setPassword(passwordEncoder.encode(patientDto.getPassword()));
        patient.setUsername(patientDto.getUsername());
        patient.setPhone(patientDto.getPhone());
        patient.setEmail(patientDto.getEmail());
        return patientRepository.save(patient);
    }


    public User fetchPatientById(String id) {
        Optional<User> patient = patientRepository.findById(id);
        if (patient.isEmpty()) {
            throw new NotFoundException("patient with email id not found");
        }
        return patient.get();
    }

    public User updatePatientById(String id, User patient) {
        Optional<User> foundPatient = patientRepository.findById(id);
        if (foundPatient.isEmpty()) {
            throw new NotFoundException("patient with email id not found");
        }
        User savedPatient = foundPatient.get();
        savedPatient.setUpdatedAt(new Date(System.currentTimeMillis()));
        savedPatient.setGender(patient.getGender() == null ? savedPatient.getGender() : patient.getGender());
        savedPatient.setDob(patient.getDob() == null ? savedPatient.getDob() : patient.getDob());
        savedPatient.setPhone(patient.getPhone() == null ? savedPatient.getPhone() : patient.getPhone());
        savedPatient.setUsername(patient.getUsername() == null ? savedPatient.getUsername() : patient.getUsername());
        return patientRepository.save(savedPatient);
    }

    public void deletePatientById(String id) {
        Optional<User> patient = patientRepository.findById(id);
        if (patient.isEmpty()) {
            throw new NotFoundException("patient with email id not found");
        }
        patientRepository.deleteById(id);
    }

    public List<User> fetchPaginatedPatients(String pageNumber) {
        List<User> patients = patientRepository.findAll(PageRequest.of(Integer.parseInt(pageNumber), 15).withSort(Sort.by(Sort.Direction.DESC, "createdAt"))).toList();
        if (patients.size() == 0) {
            return new ArrayList<>();
        }
        return patients;
    }

}
