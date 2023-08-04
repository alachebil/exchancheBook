package com.workshop.bouali.dao;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class UserDao {
    //aaa

    private final static List<UserDetails> APPLICATION_USERS = Arrays.asList(
            new User(
                    "Bouali.social@gmail.com",
                    "password",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))),
            new User(
                    "user.social@gmail.com",
                    "password",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))),
            new User(
                    "user@gmail.com",
                    "password",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")))


    );


    public UserDetails findUserByEmail(String email)
    {
        System.out.println("here");
        UserDetails u1 = APPLICATION_USERS
                .stream()
                .filter(u-> u.getUsername().equals(email)).
                findFirst().
                orElseThrow(() ->new UsernameNotFoundException("no User was found "));
    return u1;
    }
}
