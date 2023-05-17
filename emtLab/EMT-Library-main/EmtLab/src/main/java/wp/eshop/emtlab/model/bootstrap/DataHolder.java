package wp.eshop.emtlab.model.bootstrap;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import wp.eshop.emtlab.model.Author;
import wp.eshop.emtlab.model.Book;
import wp.eshop.emtlab.model.Country;
import wp.eshop.emtlab.model.dto.AuthorDto;
import wp.eshop.emtlab.model.dto.BookDto;
import wp.eshop.emtlab.model.dto.CountryDto;
import wp.eshop.emtlab.model.enumerations.BookCategory;
import wp.eshop.emtlab.service.AuthorService;
import wp.eshop.emtlab.service.BookService;
import wp.eshop.emtlab.service.CountryService;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    private final CountryService countryService;
    private final BookService bookService;
    private final AuthorService authorService;
//    public static List<Country> countries = new ArrayList<>();
//    public static List<Author> authors = new ArrayList<>();
//    public static List<Book> books = new ArrayList<>();

    public DataHolder(CountryService countryService, BookService bookService, AuthorService authorService) {
        this.countryService = countryService;
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @PostConstruct
    public void init(){
        Country country = countryService.create(new CountryDto("USA","North America")).get();
        Author author = authorService.create(new AuthorDto("Colleen","Hoover",country.getId())).get();
        bookService.create(new BookDto("It ends with us", BookCategory.DRAMA, author.getId(),400));
        country = countryService.create(new CountryDto("England","Europe")).get();
        author = authorService.create(new AuthorDto("John","Marrs",country.getId())).get();
        bookService.create(new BookDto("The one", BookCategory.THRILLER, author.getId(),200));
    }
}
