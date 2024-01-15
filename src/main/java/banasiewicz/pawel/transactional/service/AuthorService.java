package banasiewicz.pawel.transactional.service;

import banasiewicz.pawel.transactional.dao.AuthorRepository;
import banasiewicz.pawel.transactional.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author>  getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    @Transactional
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Transactional
    public Author updateAuthor(Long id, Author updatedAuthor) {
        if (authorRepository.existsById(id)) {
            updatedAuthor.setId(id);
            return authorRepository.save(updatedAuthor);
        }

        return null;
    }

    @Transactional
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

}
