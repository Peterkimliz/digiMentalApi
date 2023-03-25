package com.example.digimental.security;

import com.example.digimental.models.User;
import com.example.digimental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> foundUser = userRepository.findByEmail(username);
        if (foundUser.isEmpty()) {
            throw new UsernameNotFoundException("user with email address not found");
        }

        return new org.springframework.security.core.userdetails.User(foundUser.get().getEmail(), foundUser.get().getPassword(), new ArrayList<>());
    }
}
