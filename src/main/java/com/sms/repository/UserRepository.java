package com.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sms.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
    
    // Login dhoondne ke liye
    User findByUsername(String username);
    
    // Duplicate check karne ke liye
    boolean existsByUsername(String username);
}