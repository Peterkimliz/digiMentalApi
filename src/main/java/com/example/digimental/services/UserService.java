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
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(UserDto userDto) {
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
        user.setVerified(userDto.getType().equals("patient"));
        user.setEmail(userDto.getEmail());
        return userRepository.save(user);
    }


    public User fetchUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("user with email id not found");
        }
        return user.get();
    }

    public User updateUserById(String id, User user1) {
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isEmpty()) {
            throw new NotFoundException("user with email id not found");
        }
        User user = foundUser.get();
        user.setUpdatedAt(new Date(System.currentTimeMillis()));
        user.setGender(user1.getGender() == null ? user.getGender() : user1.getGender());
        user.setDob(user1.getDob() == null ? user.getDob() : user1.getDob());
        user.setPhone(user1.getPhone() == null ? user.getPhone() : user1.getPhone());
        user.setUsername(user1.getUsername() == null ? user.getUsername() : user1.getUsername());
        return userRepository.save(user);
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

}
