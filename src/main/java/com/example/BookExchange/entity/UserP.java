package com.example.BookExchange.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserP {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String username;
    private String password;
    @ManyToMany(fetch =FetchType.EAGER)
    private Collection<Role> roles=new ArrayList<>();

    @OneToMany(mappedBy="userP")
    private List<Books> books;

    // getters and setters are not shown for brevity



    public UserP(String name, String username, String password, ArrayList<Role> roles) {

        this.name = Objects.requireNonNull(name);
        this.username = Objects.requireNonNull(username);
        this.password = this.encrypt(password);
        this.roles = Objects.requireNonNull(roles);
    }


    String encrypt(String password) {
        // encryption logic
        return password;
    }
}
