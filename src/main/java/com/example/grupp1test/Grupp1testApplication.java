package com.example.grupp1test;

import com.example.grupp1test.Models.Users;
import com.example.grupp1test.Repositories.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class Grupp1testApplication {

    public static void main(String[] args) {
        SpringApplication.run(Grupp1testApplication.class, args);


    }
    @Bean
    public CommandLineRunner demo(UserRepo userRepo) {
        return (args) -> {

            Users user1 = new Users("Kalle", "dawfsfgfvd");
            Users user2 = new Users("Adam", "fmiesog8314");
            Users user3 = new Users("Fia", "12345678");

            SecurityConfig b = new SecurityConfig();
            UserService userService = new UserService(b.passwordEncoder());
            userService.registerUser(user1.getName(),user1.getPassword());

            userRepo.save(user1);
            userRepo.save(user2);
            userRepo.save(user3);
        };
    }
}
