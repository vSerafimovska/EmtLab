package wp.eshop.emtlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wp.eshop.emtlab.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
}
