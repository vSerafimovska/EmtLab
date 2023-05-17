package wp.eshop.emtlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wp.eshop.emtlab.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
}
