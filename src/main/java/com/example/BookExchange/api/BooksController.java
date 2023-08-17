package com.example.BookExchange.api;

import com.example.BookExchange.dto.BooksDto;
import com.example.BookExchange.entity.Books;
import com.example.BookExchange.service.BooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BooksController {

    private final BooksService booksService;




//    @GetMapping("/books")
//    public ResponseEntity<List<Books>> getBooks() {
//
//        return ResponseEntity.ok().body(booksService.getBooks());
//    }


    @PostMapping("/book/save")
    public ResponseEntity<Books> saveBook(@RequestBody Books books) {
        // chnya el uri eli zedha
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/book/save").toUriString());
        return ResponseEntity.created(uri).body(booksService.addBook(books));
    }



    @DeleteMapping(path = "/del/{bookId}")
    public void deleteBook(@PathVariable("bookId") Long bookId ) throws IllegalAccessException {
        booksService.deleteBook(bookId);
    }

    @PutMapping(path = "{bookId}")
    public void updateBook(@PathVariable("bookId") Long bookId,@RequestParam(required = false) String name,@RequestParam(required = false) String categorie,@RequestParam(required = false) Long prix ) throws IllegalAccessException {
        booksService.updateBook(bookId,name,categorie,prix);
    }



    @GetMapping("/Books")

    public List<BooksDto> getUsers() {
        return booksService.getBooks();

    }

}
