package com.book.search.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.search.entity.Book;
import com.book.search.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> searchBooks(String searchTerm) {
        if(searchTerm == null || searchTerm.isEmpty()) {
            throw new IllegalArgumentException("Search term cannot be empty");
        }
        return bookRepository.searchBooks(searchTerm);
    }
    
}
