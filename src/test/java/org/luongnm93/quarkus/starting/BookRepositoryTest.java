package org.luongnm93.quarkus.starting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookRepositoryTest {

    private BookRepository repository;

    @BeforeEach
    void setUp() throws Exception {
        repository = new BookRepository();
        var field = BookRepository.class.getDeclaredField("genre");
        field.setAccessible(true);
        field.set(repository, "Sci-Fi");
    }

    @Test
    void getAllBooksShouldReturnFourBooks() {
        List<Book> books = repository.getAllBooks();
        assertEquals(4, books.size());
    }

    @Test
    void getAllBooksShouldContainExpectedTitles() {
        List<Book> books = repository.getAllBooks();
        List<String> titles = books.stream().map(b -> b.title).toList();
        assertTrue(titles.contains("Understanding Quarkus"));
        assertTrue(titles.contains("Practising Quarkus"));
        assertTrue(titles.contains("Effective Java"));
        assertTrue(titles.contains("Thinking in Java"));
    }

    @Test
    void getBookShouldReturnBookForValidId() {
        Optional<Book> book = repository.getBook(1);
        assertTrue(book.isPresent());
        assertEquals("Understanding Quarkus", book.get().title);
        assertEquals("Antonio", book.get().author);
        assertEquals(2020, book.get().yearOfPublication);
        assertEquals("Sci-Fi", book.get().genre);
    }

    @Test
    void getBookShouldReturnEmptyForUnknownId() {
        Optional<Book> book = repository.getBook(999);
        assertTrue(book.isEmpty());
    }

    @Test
    void getAllBooksShouldUseConfiguredGenre() throws Exception {
        var field = BookRepository.class.getDeclaredField("genre");
        field.setAccessible(true);
        field.set(repository, "Fantasy");

        List<Book> books = repository.getAllBooks();
        assertTrue(books.stream().allMatch(b -> "Fantasy".equals(b.genre)));
    }
}
