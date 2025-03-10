package com.book.search.service;

import java.util.List;

import com.book.search.dto.BookUpdateDTO;
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
    // Get all books
    public List<Book> getAllBooks() {
        try {
            return bookRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all books", e);
        }
    }
    // Get book by ID
    public Book getBookById(Long id) {
        try {
            return bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch book by ID", e);
        }
    }

    //Update an existing book
    public Book updateBook(Long id, BookUpdateDTO bookUpdateDTO) {
        try {
            Book existingBook = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));
            if (bookUpdateDTO.getTitle() != null) existingBook.setTitle(bookUpdateDTO.getTitle());
            if (bookUpdateDTO.getRating() != null) existingBook.setRating(bookUpdateDTO.getRating());
            if (bookUpdateDTO.getDescription() != null) existingBook.setDescription(bookUpdateDTO.getDescription());
            if (bookUpdateDTO.getLanguage() != null) existingBook.setLanguage(bookUpdateDTO.getLanguage());
            if (bookUpdateDTO.getIsbn() != null) existingBook.setIsbn(bookUpdateDTO.getIsbn());
            if (bookUpdateDTO.getBookFormat() != null) existingBook.setBookFormat(bookUpdateDTO.getBookFormat());
            if (bookUpdateDTO.getEdition() != null) existingBook.setEdition(bookUpdateDTO.getEdition());
            if (bookUpdateDTO.getPages() != 0) existingBook.setPages(bookUpdateDTO.getPages());
            if (bookUpdateDTO.getPublisher() != null) existingBook.setPublisher(bookUpdateDTO.getPublisher());
            if (bookUpdateDTO.getPublishDate() != null) existingBook.setPublishDate(bookUpdateDTO.getPublishDate());
            if (bookUpdateDTO.getFirstPublishDate() != null) existingBook.setFirstPublishDate(bookUpdateDTO.getFirstPublishDate());
            if (bookUpdateDTO.getLikedPercent() != null) existingBook.setLikedPercent(bookUpdateDTO.getLikedPercent());
            if (bookUpdateDTO.getPrice() != null) existingBook.setPrice(bookUpdateDTO.getPrice());
            return bookRepository.save(existingBook);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update book", e);
        }
    }

    // Add a new book
    public Book addBook(Book book) {
        try {
            return bookRepository.save(book);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add book", e);
        }
    }
}
