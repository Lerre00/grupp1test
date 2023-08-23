package com.example.grupp1test;

import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService {

    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(String username, String password) {
        String encryptedPassword = passwordEncoder.encode(password);
        // Store the username and encryptedPassword in your database
    }
}
