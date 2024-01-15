package banasiewicz.pawel.transactional.dao;

import banasiewicz.pawel.transactional.entity.Genre;
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
class GenreRepositoryTest {
    private final static int GENRES_COUNT = 5;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void count_ShouldReturnNumberOfGenres() {
        final long count = genreRepository.count();
        assertEquals(GENRES_COUNT, count);
    }

    @Test
    public void findById_ShouldExist() {
        final long genreId = 1;
        final Optional<Genre> genre = genreRepository.findById(genreId);
        assertTrue(genre.isPresent());
    }

    @Test
    public void findAllSize_ShouldReturnNumberOfGenres() {
        final List<Genre> allGenres = genreRepository.findAll();
        assertEquals(GENRES_COUNT, allGenres.size());
    }

    @Test
    public void save_ShouldAddNewGenre() {
        final Genre genre = new Genre("New Genre", "Description");
        genreRepository.save(genre);

        assertNotNull(genre.getId());
        assertEquals(GENRES_COUNT + 1, genreRepository.count());
    }

    @Test
    public void delete_ShouldDeleteGenreById() {
        final Long idToDelete = 1L;
        genreRepository.deleteById(idToDelete);
        assertEquals(GENRES_COUNT - 1, genreRepository.count());
    }

    @Test
    public void deleteAll_ShouldDeleteAllGenres() {
        genreRepository.deleteAll();
        genreRepository.deleteAllInBatch();
        assertEquals(0, genreRepository.count());
    }

    @Test
    public void deleteAllInBatch_ShouldDeleteAllGenres() {
        genreRepository.deleteAllInBatch();
        assertEquals(0, genreRepository.count());
    }

}