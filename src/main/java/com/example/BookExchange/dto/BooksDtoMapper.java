package com.example.BookExchange.dto;

import com.example.BookExchange.entity.Books;
import org.springframework.stereotype.Service;


import java.util.function.Function;

@Service
public class BooksDtoMapper implements Function<Books, BooksDto> {
    @Override
    public BooksDto apply(Books books) {
        return new BooksDto(books.getName(),books.getCategorie(),books.getImage(),books.getPrix());
    }
}
