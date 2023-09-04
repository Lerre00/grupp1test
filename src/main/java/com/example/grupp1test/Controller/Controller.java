package com.example.grupp1test.Controller;
import com.example.grupp1test.Models.Users;
import com.example.grupp1test.Repositories.UserRepo;
import com.example.grupp1test.config.SecurityConfig;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

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

    @PostMapping("login")
    public String logIn(Users user, Model model) throws IOException {
        Users dbUser = userRepo.findByName(user.getName());
        SecurityConfig securityConfig = new SecurityConfig();
        String filePath = "src/main/java/com/example/grupp1test/commonPasswords";
        String correctPassword = dbUser.getPassword();
        Optional<String> foundPassword = Files.lines(Paths.get(filePath))
                .map(line -> line.split(" ")[0])
                .filter(password -> securityConfig.passwordEncoder().matches(password, correctPassword))
                .findFirst();

        if (foundPassword.isPresent()) {
            model.addAttribute("message", "You're logged in!");
        } else {
            model.addAttribute("message", "Invalid username or password");
        }
        return "log-in-validator";
    }



    @RequestMapping("home")
    public String homePage(){
        return "log-in";
    }


}
