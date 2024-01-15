package banasiewicz.pawel.transactional.dao;

import banasiewicz.pawel.transactional.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
