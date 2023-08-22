package com.example.grupp1test.Controller;


import com.example.grupp1test.Models.Users;
import com.example.grupp1test.Repositories.UserRepo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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

}
