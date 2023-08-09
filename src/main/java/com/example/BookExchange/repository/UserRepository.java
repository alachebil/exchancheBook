package com.example.BookExchange.repository;

import com.example.BookExchange.entity.UserP;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserP,Long> {

    UserP findByUsername (String username);
}
