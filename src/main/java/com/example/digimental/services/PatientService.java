package com.example.digimental.services;

import com.example.digimental.dtos.PatientDto;
import com.example.digimental.exceptions.FoundException;
import com.example.digimental.exceptions.NotFoundException;
import com.example.digimental.models.Patient;
import com.example.digimental.repository.PatientRepository;
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
public class PatientService {
    @Autowired
    private  PatientRepository patientRepository;
    @Autowired
   private PasswordEncoder passwordEncoder;


    public Patient createPatient(PatientDto patientDto) {
        Optional<Patient> foundPatient = patientRepository.findByEmail(patientDto.getEmail());
        if (foundPatient.isPresent()) {
            throw new FoundException("patient with provided email already exists");
        }
        Patient patient = new Patient();
        patient.setCreatedAt(new Date(System.currentTimeMillis()));
        patient.setUpdatedAt(new Date(System.currentTimeMillis()));
        patient.setPassword(passwordEncoder.encode(patientDto.getPassword()));
        patient.setUsername(patientDto.getUsername());
        patient.setPhone(patientDto.getPhone());
        patient.setEmail(patientDto.getEmail());
        return patientRepository.save(patient);
    }


    public Patient fetchPatientById(String id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isEmpty()) {
            throw new NotFoundException("patient with email id not found");
        }
        return patient.get();
    }

    public Patient updatePatientById(String id, Patient patient) {
        Optional<Patient> foundPatient = patientRepository.findById(id);
        if (foundPatient.isEmpty()) {
            throw new NotFoundException("patient with email id not found");
        }
        Patient savedPatient = foundPatient.get();
        savedPatient.setUpdatedAt(new Date(System.currentTimeMillis()));
        savedPatient.setGender(patient.getGender() == null ? savedPatient.getGender() : patient.getGender());
        savedPatient.setDob(patient.getDob() == null ? savedPatient.getDob() : patient.getDob());
        savedPatient.setPhone(patient.getPhone() == null ? savedPatient.getPhone() : patient.getPhone());
        savedPatient.setUsername(patient.getUsername() == null ? savedPatient.getUsername() : patient.getUsername());
        return patientRepository.save(savedPatient);
    }

    public void deletePatientById(String id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isEmpty()) {
            throw new NotFoundException("patient with email id not found");
        }
        patientRepository.deleteById(id);
    }

    public List<Patient> fetchPaginatedPatients(String pageNumber) {
        List<Patient> patients = patientRepository.findAll(PageRequest.of(Integer.parseInt(pageNumber), 15).withSort(Sort.by(Sort.Direction.DESC, "createdAt"))).toList();
        if (patients.size() == 0) {
            return new ArrayList<>();
        }
        return patients;
    }

}
