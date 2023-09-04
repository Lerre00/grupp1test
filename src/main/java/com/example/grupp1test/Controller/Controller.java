package com.example.grupp1test.Controller;


import com.example.grupp1test.Models.Users;
import com.example.grupp1test.Repositories.UserRepo;
import com.example.grupp1test.SecurityConfig;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Controller
@RequestMapping
public class Controller {

    private final UserRepo userRepo;

    public Controller(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @RequestMapping("users")
    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

    @PostMapping("login")
    public String logIn(Users user, Model model) throws IOException {
        Users dbUser = userRepo.findByName(user.getName());
        SecurityConfig securityConfig = new SecurityConfig();
        String filePath = "src/main/java/com/example/grupp1test/commonPasswords";
        Optional<String> foundPassword = Files.lines(Paths.get(filePath))
                .map(line -> line.split(" ")[0])
                .filter(password -> dbUser != null && securityConfig.passwordEncoder().matches(password, dbUser.getPassword()))
                .findFirst();

        if (foundPassword.isPresent()) {
            model.addAttribute("message", "You're logged in!");
            return "log-in-validator";
        } else {
            model.addAttribute("message", "Invalid username or password");
            return "log-in-validator";
        }
    }

    @RequestMapping("home")
    public String homePage() {
        return "log-in";
    }

    public int hack(int password) {
        return (password += 1);
    }
}
