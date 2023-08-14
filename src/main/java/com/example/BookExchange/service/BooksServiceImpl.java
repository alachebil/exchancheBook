package com.example.BookExchange.service;

import com.example.BookExchange.entity.Books;
import com.example.BookExchange.entity.UserP;
import com.example.BookExchange.repository.BooksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional // mech mafhoumaa chnyaaa
public class BooksServiceImpl implements BooksService{

    private final BooksRepository booksRepository;

    @Override
    public Books addBook(Books books) {
        return booksRepository.save(books);
    }

    @Override
    public Books getBook(String name) {
        return booksRepository.findByname(name);
    }

    @Override
    public List<Books> getBooks() {
        return booksRepository.findAll();
    }

    @Override
    public void deleteBook(Long bookId) throws IllegalAccessException {


        boolean exists = booksRepository.existsById(bookId);
        if (!exists) {
            throw new IllegalAccessException("user with id " + bookId + " is not exist ");
        } else
            booksRepository.deleteById(bookId);
        System.out.println("the student is deleted successfully");

    }

    @Override
    @Transactional
    public void updateBook(Long bookId, String name, String categorie, Long prix) throws IllegalAccessException {


        Books books = booksRepository.findById(bookId).orElseThrow(() -> new IllegalAccessException("book with id " + bookId + " is not exist "));
        if (name != null && name.length() > 0 ) {
            books.setName(name);
        }

        if (categorie != null && categorie.length() > 0) {

            books.setCategorie(categorie);
        }
        if (prix != null ) {

            books.setPrix(prix);
        }


    }
}
