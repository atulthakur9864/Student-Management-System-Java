package com.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import com.sms.entity.User;
import com.sms.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Database se user dhoondna
        com.sms.entity.User myUser = repo.findByUsername(username); 
        
        if (myUser == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        // 2. Spring Security UserDetails return karna
        return org.springframework.security.core.userdetails.User
                .withUsername(myUser.getUsername())
                .password(myUser.getPassword())
                .authorities("ROLE_USER") 
                .build();
    }
}