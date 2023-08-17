package com.example.BookExchange.dto;

import lombok.Data;

@Data
public class UserDTO {

    private String name;
    private String username;
    private Long tel;

    public UserDTO(String name, String username, Long tel) {
        this.name = name;
        this.username = username;
        this.tel = tel;
    }
}
