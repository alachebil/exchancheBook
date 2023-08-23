package com.example.BookExchange.service;


import com.example.BookExchange.entity.Books;
import com.example.BookExchange.repository.BooksRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional // mech mafhoumaa chnyaaa
@Slf4j
public class ApiService {

    private final BooksRepository booksRepository;

    @Transactional
//cron job hedhi kol minute texecuti wa7adha
//    @Scheduled(cron = "0 * * * * ?")
    public void listBooks() throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "https://api.nytimes.com/svc/books/v3/lists/current/hardcover-fiction.json?api-key=2vVLk6AhTk8XAYEBzgrfZyIfFXartfn8";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl , String.class);

    String json =response.getBody();


        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode resultsNode = rootNode.get("results");
            JsonNode booksArray = resultsNode.get("books");

            for (JsonNode bookNode : booksArray) {
                String title = bookNode.get("title").asText();
                Double price = Double.parseDouble(bookNode.get("price").asText()); // Assuming "price" is a string representing a Long
                String imageUrl = bookNode.path("book_image").asText();


                Books book1= booksRepository.findByname(title);


                Books book = new Books();
                book.setName(title);
                book.setPrice(price);
                book.setCategory("new york times book"); // Set the category as needed
                book.setImage(imageUrl);
                // Set other fields
                System.out.println("Book to be saved: " + book);
                try {
                    booksRepository.save(book);
                } catch (Exception e) {
                    e.printStackTrace();
                    // Handle the exception as needed
                }            }
        } catch (IOException e) {
            // Handle exception
        }

    log.info(response.getBody());

    }
}
