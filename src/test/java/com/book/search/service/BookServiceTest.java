package com.book.search.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.any;

import com.book.search.entity.Book;
import com.book.search.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
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
    @Test
    public void testAddBook_Success() {
        // Create a new Book object and set its title
        Book book = new Book();
        book.setTitle("Test Book");

        // Create a savedBook object to simulate the saved book returned by the repository
        Book savedBook = new Book();
        savedBook.setBookId(1L);
        savedBook.setTitle("Test Book");

        // Mock the behavior of the bookRepository.save method to return the savedBook object
        when(bookRepository.save(any(Book.class))).thenReturn(savedBook);

        // Call the addBook method of the bookService and store the result
        Book result = bookService.addBook(book);

        // Verify that the bookRepository.save method was called once with the book object
        verify(bookRepository, times(1)).save(book);
        // Verify that the bookRepository.updateSearchVector method was called once with the savedBook's ID
        verify(bookRepository, times(1)).updateSearchVector(savedBook.getBookId());

        // Assert that the result of the addBook method is equal to the savedBook object
        assertEquals(savedBook, result);
    }

}