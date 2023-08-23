package com.example.BookExchange.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "UserP")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String username;
    private String email;
    private Long tel;
    private String password;
    @ManyToMany(fetch =FetchType.EAGER)
    private Collection<Role> roles=new ArrayList<>();

    @OneToMany(mappedBy="user")
    private List<Books> books;

    // getters and setters are not shown for brevity



    public User(String name, String username, String password, Long tel,String email, ArrayList<Role> roles) {

        this.name = Objects.requireNonNull(name);
        this.username = Objects.requireNonNull(username);
        this.password = this.encrypt(password);
        this.roles = Objects.requireNonNull(roles);
        this.tel=tel;
        this.email=email;
    }


    String encrypt(String password) {
        // encryption logic
        return password;
    }
}
