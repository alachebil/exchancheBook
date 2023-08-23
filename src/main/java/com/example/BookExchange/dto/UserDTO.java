package com.example.BookExchange.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor


public class UserDTO {

    private String name;
    private String username;
    private String email;
    private Long tel;


    public UserDTO(String name, String username,String email,Long tel) {
        this.name = name;
        this.username = username;
        this.tel = tel;
        this.email = email;
    }

//    String encrypt(String password) {
//        // encryption logic
//        return password;
//    }


}
