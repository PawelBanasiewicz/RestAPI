package banasiewicz.pawel.transactional.dao;

import banasiewicz.pawel.transactional.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
