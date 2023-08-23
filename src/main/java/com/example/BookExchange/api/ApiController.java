package com.example.BookExchange.api;

import com.example.BookExchange.service.ApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apiBook")
@RequiredArgsConstructor
public class ApiController {

    @Autowired
    private final ApiService apiService;


    private final ObjectMapper objectMapper;
    @GetMapping("/convert")

    public void convertJsonToObject() throws Exception {
      apiService.listBooks();
    }





        }
