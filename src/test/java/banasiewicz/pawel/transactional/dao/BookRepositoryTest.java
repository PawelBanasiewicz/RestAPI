package banasiewicz.pawel.transactional.dao;

import banasiewicz.pawel.transactional.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
@Transactional
class BookRepositoryTest {
    private final static int BOOKS_COUNT = 10;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void count_ShouldReturnNumberOfBooks() {
        final long count = bookRepository.count();
        assertEquals(BOOKS_COUNT, count);
    }

    @Test
    public void findById_ShouldExist() {
        final long bookId = 1;
        final Optional<Book> book = bookRepository.findById(bookId);
        assertTrue(book.isPresent());
    }

    @Test
    public void findAllSize_ShouldReturnNumberOfBooks() {
        final List<Book> allBooks = bookRepository.findAll();
        assertEquals(BOOKS_COUNT, allBooks.size());
    }

    @Test
    public void save_ShouldAddNewBook() {
        final Book book = new Book("new book", "new author");
        bookRepository.save(book);

        assertNotNull(book.getId());
        assertEquals(BOOKS_COUNT + 1, bookRepository.count());
    }

    @Test
    public void delete_ShouldDeleteBookById() {
        final Long idToDelete = 1L;
        bookRepository.deleteById(idToDelete);
        assertEquals(BOOKS_COUNT - 1, bookRepository.count());
    }

    @Test
    public void deleteAll_ShouldDeleteAllBooks() {
        bookRepository.deleteAll();
        bookRepository.deleteAllInBatch();
        assertEquals(0, bookRepository.count());
    }

    @Test
    public void deleteAllInBatch_ShouldDeleteAllBooks() {
        bookRepository.deleteAllInBatch();
        assertEquals(0, bookRepository.count());
    }

}