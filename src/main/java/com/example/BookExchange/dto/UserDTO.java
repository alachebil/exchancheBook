package com.example.BookExchange.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor


public class UserDTO {

    private String name;
    private String username;
    private Long tel;


    public UserDTO(String name, String username, Long tel) {
        this.name = name;
        this.username = username;
        this.tel = tel;
    }

//    String encrypt(String password) {
//        // encryption logic
//        return password;
//    }


}
