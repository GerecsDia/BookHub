package com.geolidth.BackEnd.models.dao;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 100)
    private String title;
    @Column(length = 50)
    private String author;
    @Column(length = 50)
    private String publisher;
    @Column(length = 30)
    private String category;
    @Column(length = 50)
    private String county;
    @Column(length = 50)
    private String quality;
    @Column(length = 6)
    private Integer year;
    @Column(name = "reserved")
    private Boolean reserved;
    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("owner_Id")
    private BookUser owner;

    public Book(Integer id, String title, String author, String publisher, String category, String county, String quality, Integer year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
        this.county = county;
        this.quality = quality;
        this.year = year;
    }
}


