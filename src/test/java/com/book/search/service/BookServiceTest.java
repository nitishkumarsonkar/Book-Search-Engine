package com.book.search.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.book.search.entity.Book;
import com.book.search.repository.BookRepository;




@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    // @Mock
    // private BookRepository bookRepository;

    // @BeforeEach
    // public void setUp() {
    //     MockitoAnnotations.openMocks(this);
    // }

    @Test
    public void testSearchBooks_ValidSearchTerm() {
        String searchTerm = "algorithm";
        // List<Book> expectedBooks = Arrays.asList(new Book("Effective Java"), new Book("Java Concurrency in Practice"));
        
        // when(bookRepository.searchBooks(searchTerm)).thenReturn(expectedBooks);
        
        // List<Book> actualBooks = bookService.searchBooks(searchTerm);
        // assertTrue(actualBooks.size()>0);
        
        // assertEquals(expectedBooks, actualBooks);
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