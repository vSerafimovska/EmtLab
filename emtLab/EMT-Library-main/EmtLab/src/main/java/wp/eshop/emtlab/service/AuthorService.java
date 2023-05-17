package wp.eshop.emtlab.service;


import wp.eshop.emtlab.model.Author;
import wp.eshop.emtlab.model.Book;
import wp.eshop.emtlab.model.dto.AuthorDto;
import wp.eshop.emtlab.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();

    Optional<Author> findById(Long id);

    Optional<Author> create(AuthorDto authorDto);

    Optional<Author> update(Long id, AuthorDto authorDto);

    void delete(Long id);
}
