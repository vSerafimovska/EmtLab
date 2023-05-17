package wp.eshop.emtlab.service.impl;

import org.springframework.stereotype.Service;
import wp.eshop.emtlab.model.Author;
import wp.eshop.emtlab.model.Book;
import wp.eshop.emtlab.model.dto.BookDto;
import wp.eshop.emtlab.model.enumerations.BookCategory;
import wp.eshop.emtlab.model.exceptions.AuthorNotFoundException;
import wp.eshop.emtlab.model.exceptions.BookNotFoundException;
import wp.eshop.emtlab.model.exceptions.NotEnoughReturnedBooksException;
import wp.eshop.emtlab.repository.AuthorRepository;
import wp.eshop.emtlab.repository.BookRepository;
import wp.eshop.emtlab.service.BookService;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> create(BookDto bookDto) {
        String name = bookDto.getName();
        if(name.isEmpty()){
            return Optional.empty();
        }
        BookCategory bookCategory = bookDto.getCategory();
        Author author = authorRepository.findById(bookDto.getAuthorId()).orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthorId()));
        Integer availableCopies = bookDto.getAvailableCopies();
        return Optional.of(bookRepository.save(new Book(name,bookCategory,author,availableCopies)));
    }

    @Override
    public Optional<Book> update(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        book.setName(bookDto.getName());
        book.setBookCategory(bookDto.getCategory());
        Author author = authorRepository.findById(bookDto.getAuthorId()).orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthorId()));
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> reserveBooks(Long id, Integer numRequested) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Integer available = book.getAvailableCopies();
        if(available < numRequested){
            throw new NotEnoughReturnedBooksException();
        }
        book.setAvailableCopies(available-numRequested);
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Optional<Book> returnBooks(Long id, Integer numReturned) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Integer available = book.getAvailableCopies();
        if(numReturned<0){
            throw new NotEnoughReturnedBooksException();
        }
        book.setAvailableCopies(available+numReturned);
        return Optional.of(bookRepository.save(book));
    }
}
