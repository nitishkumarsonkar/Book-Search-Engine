package com.book.search.service;

import java.util.List;

import com.book.search.dto.BookUpdateDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.search.entity.Book;
import com.book.search.repository.BookRepository;

@Service
public class BookService {

    //adding logger
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;

    public List<Book> searchBooks(String searchTerm) {
        if(searchTerm == null || searchTerm.isEmpty()) {
            logger.warn("Empty search term provided");
            throw new IllegalArgumentException("Search term cannot be empty");
        }
        logger.info("searching books with search term: {}", searchTerm);
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
            Book savedBook = bookRepository.save(existingBook);
            bookRepository.updateSearchVector(savedBook.getBookId());
            //return bookRepository.save(existingBook);
            return savedBook;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update book", e);
        }
    }

    // Add a new book
    public Book addBook(Book book) {
        try {
            logger.info("Adding a new book with title: {}", book.getTitle());
            Book savedBook = bookRepository.save(book);
            bookRepository.updateSearchVector(savedBook.getBookId());
            logger.info("Book added successfully with ID: {}", savedBook.getBookId());
            return savedBook;
        } catch (Exception e) {
            logger.info("Failed to add book with title: {}", book.getTitle());
            throw new RuntimeException("Failed to add book", e);
        }
    }
}
