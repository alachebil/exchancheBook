package com.workshop.bouali.User;

import lombok.Data;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dob;
    private String email;
    private String name;
    private Long tel;

    // Constructors, getters, and setters
}