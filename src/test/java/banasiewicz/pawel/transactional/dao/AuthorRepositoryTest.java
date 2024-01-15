package banasiewicz.pawel.transactional.dao;

import banasiewicz.pawel.transactional.entity.Author;
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
class AuthorRepositoryTest {
    private final static int AUTHORS_COUNT = 5;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void count_ShouldReturnNumberOfAuthors() {
        final long count = authorRepository.count();
        assertEquals(AUTHORS_COUNT, count);
    }

    @Test
    public void findById_ShouldExist() {
        final long authorId = 1;
        final Optional<Author> author = authorRepository.findById(authorId);
        assertTrue(author.isPresent());
    }

    @Test
    public void findAllSize_ShouldReturnNumberOfAuthors() {
        final List<Author> allAuthors = authorRepository.findAll();
        assertEquals(AUTHORS_COUNT, allAuthors.size());
    }

    @Test
    public void save_ShouldAddNewAuthor() {
        final Author author = new Author("New Author", "Nationality");
        authorRepository.save(author);

        assertNotNull(author.getId());
        assertEquals(AUTHORS_COUNT + 1, authorRepository.count());
    }

    @Test
    public void delete_ShouldDeleteAuthorById() {
        final Long idToDelete = 1L;
        authorRepository.deleteById(idToDelete);
        assertEquals(AUTHORS_COUNT - 1, authorRepository.count());
    }

    @Test
    public void deleteAll_ShouldDeleteAllAuthors() {
        authorRepository.deleteAll();
        authorRepository.deleteAllInBatch();
        assertEquals(0, authorRepository.count());
    }

    @Test
    public void deleteAllInBatch_ShouldDeleteAllAuthors() {
        authorRepository.deleteAllInBatch();
        assertEquals(0, authorRepository.count());
    }
}