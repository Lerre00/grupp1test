package com.example.grupp1test.Controller;


import com.example.grupp1test.Models.Users;
import com.example.grupp1test.Repositories.UserRepo;
import com.example.grupp1test.config.SecurityConfig;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@org.springframework.stereotype.Controller
@RequestMapping
public class Controller {

    private final UserRepo userRepo;

    public Controller(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @RequestMapping("users")
    public List<Users> getAllUsers(){
        return userRepo.findAll();
    }

    // Define SecurityConfig as a class variable (assuming it's a singleton)
    private final SecurityConfig securityConfig = new SecurityConfig();

    // Define a set to store common passwords (initialized during application startup)
    private final Set<String> commonPasswords = loadEncodedCommonPasswords();

    // Load common passwords into a set during application startup
    private Set<String> loadEncodedCommonPasswords() {
        String filePath = "src/main/java/com/example/grupp1test/commonPasswords";
        Set<String> encodedPasswords = new HashSet<>();
        BCryptPasswordEncoder passwordEncoder = securityConfig.passwordEncoder();

        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            lines.map(line -> line.split(" ")[0])
                    .map(passwordEncoder::encode)
                    .forEach(encodedPasswords::add);
        } catch (IOException e) {
            // Handle file reading error during application startup
            // Log the error or take appropriate action
        }

        return encodedPasswords;
    }


    @PostMapping("login")
    public String logIn(Users user, Model model) {
        int counter = 0;
        for (String s: commonPasswords){
            if(counter < 5){
                System.out.println(s);
                counter++;
            }
            else{
                break;
            }
        }
        Users dbUser = userRepo.findByName(user.getName());
        String password = (dbUser != null) ? dbUser.getPassword() : null;
        System.out.println("Password: " + password);

        if (password != null) {
            if (commonPasswords.contains(password)) {
                model.addAttribute("message", "Invalid username or password");
            } else if (securityConfig.passwordEncoder().matches(password, dbUser.getPassword())) {
                model.addAttribute("message", "You're logged in!");
            } else {
                model.addAttribute("message", "Invalid username or password");
            }
        } else {
            model.addAttribute("message", "Invalid username or password");
        }

        System.out.println(dbUser);
        return "log-in-validator";
    }



    @RequestMapping("home")
    public String homePage(){
        return "log-in";
    }


}
