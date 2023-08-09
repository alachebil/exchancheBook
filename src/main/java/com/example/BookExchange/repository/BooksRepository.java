package com.example.BookExchange.repository;

import com.example.BookExchange.entity.Books;

import com.example.BookExchange.entity.UserP;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books,Long> {
    Books findByname (String name);
}
