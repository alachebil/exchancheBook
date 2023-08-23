package com.example.BookExchange.service;

import com.example.BookExchange.dto.BooksDto;
import com.example.BookExchange.dto.BooksDtoMapper;
import com.example.BookExchange.entity.Books;
import com.example.BookExchange.repository.BooksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional // mech mafhoumaa chnyaaa
public class BooksServiceImpl implements BooksService{

    private final BooksRepository booksRepository;
    private final BooksDtoMapper booksDtoMapper;

    @Override
    public Books addBook(Books books) {
        return booksRepository.save(books);
    }

    @Override
    public Books getBook(String name) {
        return booksRepository.findByname(name);
    }

    @Override
    public List<BooksDto> getBooks() {
        return booksRepository.findAll().stream()
                .map(booksDtoMapper)
                .collect(Collectors.toList());
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
    public void updateBook(Long bookId, String name, String categorie, Double prix) throws IllegalAccessException {


        Books books = booksRepository.findById(bookId).orElseThrow(() -> new IllegalAccessException("book with id " + bookId + " is not exist "));
        if (name != null && name.length() > 0 ) {
            books.setName(name);
        }

        if (categorie != null && categorie.length() > 0) {

            books.setCategory(categorie);
        }
        if (prix != null ) {

            books.setPrice(prix);
        }


    }
}
