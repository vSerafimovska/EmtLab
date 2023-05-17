package wp.eshop.emtlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wp.eshop.emtlab.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
}
