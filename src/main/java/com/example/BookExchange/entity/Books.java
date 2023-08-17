package com.example.BookExchange.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String categorie;
    private String image;
    private Long prix;
    private boolean privacy;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserP userP;

    public Books(String name, String categorie, String image, Long prix) {
        this.name = name;
        this.categorie = categorie;
        this.image = image;
        this.prix = prix;
    }
}
