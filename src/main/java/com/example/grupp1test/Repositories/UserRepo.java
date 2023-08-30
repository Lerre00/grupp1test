package com.example.grupp1test.Repositories;

import com.example.grupp1test.Models.Users;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users,Long> {
    Users findByName(String name);
    Users findByPassword(String password);
}
