package com.example.BookExchange.entity;

import lombok.Data;

@Data
public class UserDTO {

    private String name;
    private String username;
    private String password;

    public UserDTO(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
