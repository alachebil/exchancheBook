package com.example.BookExchange.service;

import com.example.BookExchange.entity.Role;
import com.example.BookExchange.entity.UserP;

import java.util.List;

public interface UserService {

    UserP saveUser(UserP userP);
    Role saveRole (Role role);
    void addRoleToUser(String username, String roleName);
    UserP getUser(String username);
    List<UserP> getUsers();
}
