package com.example.BookExchange.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoCreation {


    private String name;
    private String username;
    private String email;
    private Long tel;
    private String password;
}
