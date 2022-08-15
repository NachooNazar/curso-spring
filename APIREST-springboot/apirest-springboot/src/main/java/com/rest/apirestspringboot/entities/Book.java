package com.rest.apirestspringboot.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book {

    @Getter @Setter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private LocalDate releaseDate;
    @Getter @Setter
    private String author;
    @Getter @Setter
    private Integer pages;
    @Getter @Setter
    private Double price;
    @Getter @Setter
    private Boolean online;

    public Book(Long id, String name, LocalDate releaseDate, String author, Integer pages, Double price, Boolean online) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.author = author;
        this.pages = pages;
        this.price = price;
        this.online = online;
    }

    public Book() {}

}
