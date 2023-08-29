package com.example.grupp1test.Controller;


import com.example.grupp1test.Models.Users;
import com.example.grupp1test.Repositories.UserRepo;
import com.example.grupp1test.SecurityConfig;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping
public class Controller {

    private final UserRepo userRepo;

    public Controller(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @RequestMapping("users")
    public List<Users> getAllUsers(){
        return userRepo.findAll();
    }

    @PostMapping("login")
    public String logIn(Users user, Model model){
            Users dbUser = userRepo.findByName(user.getName());
            SecurityConfig securityConfig = new SecurityConfig();

            if (dbUser != null && securityConfig.passwordEncoder().matches(user.getPassword(), dbUser.getPassword())) {
                model.addAttribute("name", user.getName());
                model.addAttribute("message", "You're logged in!");
                return "log-in-validator";
            } else {
                model.addAttribute("message", "Log in failed!");
                return "log-in-validator";
            }
    }
    @RequestMapping("home")
    public String homePage(){
        return "log-in";
    }

}
