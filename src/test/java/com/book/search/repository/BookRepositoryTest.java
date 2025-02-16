package com.book.search.repository;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import com.book.search.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;




@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    // @Sql("/test-data.sql")
    public void testSearchBooks() {
        String searchTerm = "algorithm";
        List<Book> books = bookRepository.searchBooks(searchTerm);
        assertThat(books.size()>0);
        // assertThat(books.get(0).getTitle()).containsIgnoringCase(searchTerm);
    }
}