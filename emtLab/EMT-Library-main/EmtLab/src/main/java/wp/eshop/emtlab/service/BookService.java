package wp.eshop.emtlab.service;

import wp.eshop.emtlab.model.Book;
import wp.eshop.emtlab.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> create(BookDto bookDto);

    Optional<Book> update(Long id, BookDto bookDto);

    void delete(Long id);

    Optional<Book> reserveBooks(Long id, Integer numRequested);

    Optional<Book> returnBooks(Long id, Integer numReturned);
}
