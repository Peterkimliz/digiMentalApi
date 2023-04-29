package com.example.digimental.services;

import com.example.digimental.dtos.LoginDto;
import com.example.digimental.dtos.LoginResponse;
import com.example.digimental.dtos.UpdateUserDto;
import com.example.digimental.dtos.UserDto;
import com.example.digimental.exceptions.FoundException;
import com.example.digimental.exceptions.NotFoundException;
import com.example.digimental.models.User;
import com.example.digimental.repository.UserRepository;
import com.example.digimental.security.JwtService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    EmailService emailService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtUtils;
    @Autowired
    AuthenticationManager authenticationManager;

    public LoginResponse createUser(UserDto userDto) {
        Optional<User> foundUser = userRepository.findByEmail(userDto.getEmail());

        if (foundUser.isPresent()) {
            throw new FoundException("user with provided email already exists");
        }
        User user = new User();
        user.setCreatedAt(new Date(System.currentTimeMillis()));
        user.setUpdatedAt(new Date(System.currentTimeMillis()));
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setUsername(userDto.getUsername());
        user.setPhone(userDto.getPhone());
        user.setType(userDto.getType());
        user.setIsVerified(userDto.getType().equals("patient"));
        user.setEmail(userDto.getEmail());
        createUserToFirebase(user);

        String token = jwtUtils.generateToken(user.getEmail());
        userRepository.save(user);
        return new LoginResponse(user, token);
    }


    public User fetchUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("user with email id not found");
        }
        return user.get();
    }

    public User updateUserById(String id, UpdateUserDto updateUserDto) {
        Firestore firestore = FirestoreClient.getFirestore();
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isEmpty()) {
            throw new NotFoundException("user with email id not found");
        }
        User user = foundUser.get();
        user.setUpdatedAt(new Date(System.currentTimeMillis()));
        user.setGender(updateUserDto.getGender() == null ? user.getGender() : updateUserDto.getGender());
        user.setDob(updateUserDto.getDob() == null ? user.getDob() : updateUserDto.getDob());
        user.setPhone(updateUserDto.getPhone() == null ? user.getPhone() : updateUserDto.getPhone());
        user.setAge(updateUserDto.getAge() == null ? user.getAge() : updateUserDto.getAge());
        user.setProfileImage(updateUserDto.getProfileImage() == null ? user.getProfileImage() : updateUserDto.getProfileImage());
        user.setUsername(updateUserDto.getUsername() == null ? user.getUsername() : updateUserDto.getUsername());
        user.setBio(updateUserDto.getBio() == null ? user.getBio() : updateUserDto.getBio());
        user.setCategory(updateUserDto.getCategory() == null ? user.getCategory() : updateUserDto.getCategory());
        user.setCerturl(updateUserDto.getCerturl() == null ? user.getCerturl() : updateUserDto.getCerturl());
        user.setConsultationFee(updateUserDto.getConsultationFee() == null ? user.getConsultationFee() : Integer.parseInt(updateUserDto.getConsultationFee()));
        user.setYearsOfExperience(updateUserDto.getYearsOfExperience() == null ? user.getYearsOfExperience() : Integer.parseInt(updateUserDto.getYearsOfExperience()));
        user.setCountry(updateUserDto.getCountry() == null ? user.getCountry() : updateUserDto.getCountry());
        user.setCounty(updateUserDto.getCounty() == null ? user.getCounty() : updateUserDto.getCounty());
        user.setSubcounty(updateUserDto.getSubcounty() == null ? user.getSubcounty() : updateUserDto.getSubcounty());
        user.setWorkingDays(updateUserDto.getWorkingDays() == null ? user.getWorkingDays() : updateUserDto.getWorkingDays());
        User updateduser = userRepository.save(user);
        ApiFuture<WriteResult> apiFuture = firestore.collection("users").document(updateduser.getId()).set(updateduser);
        return updateduser;
    }


    public User uploadDoctorDetailsUserById(String id, UpdateUserDto updateUserDto) {
        User user = updateUserById(id, updateUserDto);
        if (updateUserDto.getMailSend()) {
            emailService.sendEmail(user.getEmail(), "peterkironji8@gmail.com", "Please Validate My Account", "Account Verification");
        }
        return user;
    }

    public void deleteuserById(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("user with email id not found");
        }
        userRepository.deleteById(id);
    }

    public List<User> fetchPaginatedUsers(String pageNumber) {
        List<User> users = userRepository.findAll(PageRequest.of(Integer.parseInt(pageNumber), 15).withSort(Sort.by(Sort.Direction.DESC, "createdAt"))).toList();
        if (users.size() == 0) {
            return new ArrayList<>();
        }
        return users;
    }

    public LoginResponse loginUser(LoginDto loginDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
            );

        } catch (Exception e) {
            System.out.println(e);
            throw new NotFoundException("invalid email or password");
        }
        Optional<User> user = userRepository.findByEmail(loginDto.getEmail());
        String token = jwtUtils.generateToken(loginDto.getEmail());
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUser(user.get());
        loginResponse.setToken(token);

        return loginResponse;
    }

    public  List<User> searchusersByCategory(String category, int pageNumber){
        if (category.equals("all")){
            return  findAllPaginateDoctors(pageNumber);
        }else{
            Pageable paging =  PageRequest.of(pageNumber, 15).withSort(Sort.Direction.DESC,"createdAt");
            List <String> categories=new ArrayList<>();
            categories.add(category);
            Page<User>  users =userRepository.findByCategoryAndTypeAndIsVerified(categories,"doctor",true,paging);
            return  users.toList();
        }

    }

    private List<User> findAllPaginateDoctors(int pageNumber){
        Pageable paging =  PageRequest.of(pageNumber, 15).withSort(Sort.Direction.DESC,"createdAt");
        Page<User>  users= userRepository.findByTypeAndIsVerified("doctor",true,paging);
        return  users.toList();
    }


    public void createUserToFirebase(User user) {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection("users").document();
        user.setId(documentReference.getId());
        ApiFuture<WriteResult> apiFuture = documentReference.set(user);
        System.out.println(apiFuture);
    }


    public void verifyDoctor(String uid) {
        User user = fetchUserById(uid);
        if (!user.getIsVerified()) {
            user.setIsVerified(true);
            emailService.sendEmail("digimhealth@gmail.com", user.getEmail(), "Your Account has been verified", "Account Verification");
        }

        userRepository.save(user);
    }

}
