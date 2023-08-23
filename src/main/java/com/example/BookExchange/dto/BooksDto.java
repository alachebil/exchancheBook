package com.example.BookExchange.dto;

import lombok.Data;

@Data
public class BooksDto {

    private String name;
    private String categorie;
    private String image;
    private Double prix;

    public BooksDto(String name, String categorie, String image, Double prix) {
        this.name = name;
        this.categorie = categorie;
        this.image = image;
        this.prix = prix;
    }
}
