package com.book.search.controller;

import java.util.List;

import com.book.search.dto.BookUpdateDTO;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.book.search.entity.Book;

import com.book.search.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(BookController.class);
    
    @Autowired
    private BookService bookService;

    //adding a new endpoint to add new book
    @PostMapping("/add")
    public Book addBook(@RequestBody Book book) {
        logger.info("Received request to add book: {}", book);
        return bookService.addBook(book);
    }

    //adding a new endpoint to search books
    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String searchTerm) {
        logger.info("Received request to search books with search term: {}", searchTerm);
        return bookService.searchBooks(searchTerm);
    }

    // adding a new endpoint to get all books
    @GetMapping("/all")
    public List<Book> getAllBooks() {
        logger.info("Received request to fetch all books");
        return bookService.getAllBooks();
    }
    // adding a new endpoint to get book by ID
    @GetMapping("/id")
    public Book getBookById(@RequestParam Long id) {
        logger.info("Received request to fetch book by ID: {}", id);
        return bookService.getBookById(id);
    }
    // adding a new endpoint to update an existing book

    @PutMapping("/update")
    public Book updateBook(@RequestParam Long id, @RequestBody BookUpdateDTO bookUpdateDTO) {
        logger.info("Received request to update book with ID: {}", id);
        return bookService.updateBook(id, bookUpdateDTO);
    }


}