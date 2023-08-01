package com.workshop.bouali.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/greetings")
public class GreetingsController {
@GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("HELLO FROM OUR API ");
    }


    @GetMapping("/saygoodbye")
    public ResponseEntity<String> sayGoodBye(){
        return ResponseEntity.ok("GOOD BYE SEE YOU LATER  ");
    }
}
