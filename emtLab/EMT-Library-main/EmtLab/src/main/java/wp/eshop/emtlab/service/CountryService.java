package wp.eshop.emtlab.service;

import wp.eshop.emtlab.model.Book;
import wp.eshop.emtlab.model.Country;
import wp.eshop.emtlab.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();

    Optional<Country> findById(Long id);

    Optional<Country> create(CountryDto countryDto);

    void delete(Long id);
}
