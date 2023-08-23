package com.example.BookExchange.service;

import com.example.BookExchange.dto.BooksDto;
import com.example.BookExchange.entity.Books;

import java.util.List;

public interface BooksService {

    Books addBook(Books books);

    Books getBook(String name);
    List<BooksDto> getBooks();
    public void deleteBook(Long bookId) throws IllegalAccessException;
    public void updateBook(Long bookId, String name, String categorie,Double prix) throws IllegalAccessException;


}
