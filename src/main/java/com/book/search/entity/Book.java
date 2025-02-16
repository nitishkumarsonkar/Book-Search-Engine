package com.book.search.entity;
import java.math.BigDecimal;
import java.sql.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String title;
    private BigDecimal rating;
    private String description;
    private String language;
    private String isbn;
    private String bookFormat;
    private String edition;
    private int pages;
    private String publisher;
    private Date publishDate;
    private Date firstPublishDate;
    private BigDecimal likedPercent;
    private BigDecimal price;

    @Column(name  = "search_vector", columnDefinition = "tsvector")
    private String searchVector;
    
}
