package com.book.search.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.Arrays;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.book.search.entity.Book;
import com.book.search.service.BookService;






@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    // @Autowired
    // private MockMvc mockMvc;

    // @MockBean
    // private BookService bookService;

    // @InjectMocks
    // private BookController bookController;

    // @BeforeEach
    // public void setup() {
    //     MockitoAnnotations.openMocks(this);
    //     this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    // }

    @Test
    public void testSearchBooks() throws Exception {
        String searchTerm = "algorithm";

        // ResponseEntity<Book[]> response = restTemplate.getForEntity("/books/search/{searchTerm}", Book[].class, searchTerm);

        // Book[] books = response.getBody();
        // assertNotNull(books);
        // assertTrue(books.length > 0);

        // for (Book book : books) {
        //     System.out.println(book);
        // }

        // Book book1 = new Book(1L, "Book One", "Author One");
        // Book book2 = new Book(2L, "Book Two", "Author Two");
        // List<Book> books = Arrays.asList(book1, book2);

        // when(bookService.searchBooks("Book")).thenReturn(books);

        // mockMvc.perform(get("/books/search")
        //         .param("searchTerm", "Book"))
        //         .andExpect(status().isOk())
        //         .andExpect(jsonPath("$[0].id").value(book1.getId()))
        //         .andExpect(jsonPath("$[0].title").value(book1.getTitle()))
        //         .andExpect(jsonPath("$[0].author").value(book1.getAuthor()))
        //         .andExpect(jsonPath("$[1].id").value(book2.getId()))
        //         .andExpect(jsonPath("$[1].title").value(book2.getTitle()))
        //         .andExpect(jsonPath("$[1].author").value(book2.getAuthor()));
    }
}