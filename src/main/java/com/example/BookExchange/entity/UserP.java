package com.example.BookExchange.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

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

    // getters and setters are not shown for brevity

}
