package banasiewicz.pawel.transactional.service;

import banasiewicz.pawel.transactional.dao.GenreRepository;
import banasiewicz.pawel.transactional.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Optional<Genre> getGenreById(Long id) {
        return genreRepository.findById(id);
    }

    @Transactional
    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Transactional
    public Genre updateGenre(Long id, Genre updatedGenre) {
        if (genreRepository.existsById(id)) {
            updatedGenre.setId(id);
            return genreRepository.save(updatedGenre);
        }

        return null;
    }

    @Transactional
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }
}
