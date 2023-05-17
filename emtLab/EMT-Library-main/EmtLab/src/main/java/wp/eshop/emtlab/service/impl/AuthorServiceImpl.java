package wp.eshop.emtlab.service.impl;

import org.springframework.stereotype.Service;
import wp.eshop.emtlab.model.Author;
import wp.eshop.emtlab.model.Country;
import wp.eshop.emtlab.model.dto.AuthorDto;
import wp.eshop.emtlab.model.exceptions.AuthorNotFoundException;
import wp.eshop.emtlab.model.exceptions.CountryNotFoundException;
import wp.eshop.emtlab.repository.AuthorRepository;
import wp.eshop.emtlab.repository.CountryRepository;
import wp.eshop.emtlab.service.AuthorService;
import wp.eshop.emtlab.service.CountryService;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepository;
    private final CountryService countryService;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> create(AuthorDto authorDto) {
        String name = authorDto.getName();
        String surname = authorDto.getSurname();
        Country country = countryService.findById(authorDto.getCountryId()).orElseThrow(() -> new CountryNotFoundException(authorDto.getCountryId()));
        if(name.isEmpty() || surname.isEmpty())
            return Optional.empty();
        return Optional.of(authorRepository.save(new Author(name,surname,country)));
    }

    @Override
    public Optional<Author> update(Long id, AuthorDto authorDto) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        String name = authorDto.getName();
        String surname = authorDto.getSurname();
        Country country = countryService.findById(authorDto.getCountryId()).orElseThrow(() -> new CountryNotFoundException(authorDto.getCountryId()));
        author.setName(name);
        author.setCountry(country);
        author.setSurname(surname);
        return Optional.of(authorRepository.save(author));
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
