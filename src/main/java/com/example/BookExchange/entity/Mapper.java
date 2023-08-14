package com.example.BookExchange.entity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Mapper {

    public UserDTO toDto(UserP user) {
        String name = user.getName();
        String username = user.getUsername();
        String password = user.getPassword();
//        List<String> roles = user
//                .getRoles()
//                .stream()
//                .map(Role::getName)
//                .collect(toList());

//        return new UserDTO(name, username,roles);
        return new UserDTO(name ,username,password);
    }


    public UserP toUser(UserCreationDTO userDTO) {
        return new UserP(userDTO.getName(),userDTO.getUsername() ,userDTO.getPassword(), new ArrayList<>());
    }
}
