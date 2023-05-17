package wp.eshop.emtlab.service.impl;

import org.springframework.stereotype.Service;
import wp.eshop.emtlab.model.Author;
import wp.eshop.emtlab.model.Country;
import wp.eshop.emtlab.model.dto.CountryDto;
import wp.eshop.emtlab.model.exceptions.CountryNotFoundException;
import wp.eshop.emtlab.repository.CountryRepository;
import wp.eshop.emtlab.service.CountryService;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Optional<Country> create(CountryDto countryDto) {
        String name = countryDto.getName();
        String continent = countryDto.getContinent();
        if(name.isEmpty())
            return Optional.empty();
        return Optional.of(countryRepository.save(new Country(name,continent)));
    }

    @Override
    public void delete(Long id) {
        countryRepository.deleteById(id);
    }
}
