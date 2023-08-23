package com.example.grupp1test.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue
    protected long id;

    protected String name;
    protected String password;
    public Users(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
