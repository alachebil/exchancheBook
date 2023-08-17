package com.example.BookExchange.service;

import com.example.BookExchange.dto.UserDTO;
import com.example.BookExchange.entity.Role;
import com.example.BookExchange.entity.UserP;

import java.util.List;

public interface UserService {

    UserP saveUser(UserP userP);
    Role saveRole (Role role);
    void addRoleToUser(String username, String roleName);
    UserP getUser(String username);
    List<UserDTO> getUsers();


    public void deleteUser(Long userId) throws IllegalAccessException;
    public void updateUser(Long userId, String name, String username,String password) throws IllegalAccessException;
}
