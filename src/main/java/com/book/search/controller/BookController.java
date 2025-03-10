package com.book.search.controller;

import java.util.List;

import com.book.search.dto.BookUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.book.search.entity.Book;

import com.book.search.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
    
    @Autowired
    private BookService bookService;

    //adding a new endpoint to add new book
    @PostMapping("/add")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    //adding a new endpoint to search books
    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String searchTerm) {
        return bookService.searchBooks(searchTerm);
    }

    // adding a new endpoint to get all books
    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
    // adding a new endpoint to get book by ID
    @GetMapping("/id")
    public Book getBookById(@RequestParam Long id) {
        return bookService.getBookById(id);
    }
    // adding a new endpoint to update an existing book

    @PutMapping("/update")
    public Book updateBook(@RequestParam Long id, @RequestBody BookUpdateDTO bookUpdateDTO) {
        return bookService.updateBook(id, bookUpdateDTO);
    }


}