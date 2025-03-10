package com.book.search.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class BookUpdateDTO {
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

    // Getters and Setters
}