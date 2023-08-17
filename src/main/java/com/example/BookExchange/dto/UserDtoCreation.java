package com.example.BookExchange.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDtoCreation {

    private String name;
    private String username;
    private Long tel;
    private String password;
}
