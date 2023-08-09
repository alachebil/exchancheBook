package com.example.BookExchange.service;

import com.example.BookExchange.entity.Books;
import com.example.BookExchange.entity.Role;
import com.example.BookExchange.entity.UserP;

import java.util.List;

public interface BooksService {

    Books addBook(Books books);

    Books getBook(String name);
    List<Books> getBooks();
    public void deleteBook(Long bookId) throws IllegalAccessException;
    public void updateBook(Long bookId, String name, String categorie,Long prix) throws IllegalAccessException;


}
