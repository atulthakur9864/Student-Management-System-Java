package com.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.sms.entity.User;
import com.sms.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists!");
        }
        // Password ko encrypt karke save kar rahe hain
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER"); // Default role
        userRepository.save(user);
    }
}