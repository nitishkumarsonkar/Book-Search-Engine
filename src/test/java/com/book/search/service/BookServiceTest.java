package com.book.search.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    
    @Test
    public void testSearchBooks_ValidSearchTerm() {
        String searchTerm = "algorithm";
    }

    @Test
    public void testSearchBooks_EmptySearchTerm() {
        String searchTerm = "";
        
        assertThrows(IllegalArgumentException.class, () -> {
            bookService.searchBooks(searchTerm);
        });
    }

    @Test
    public void testSearchBooks_NullSearchTerm() {
        String searchTerm = null;
        
        assertThrows(IllegalArgumentException.class, () -> {
            bookService.searchBooks(searchTerm);
        });
    }
}