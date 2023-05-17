package wp.eshop.emtlab.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wp.eshop.emtlab.model.Book;
import wp.eshop.emtlab.model.dto.BookDto;
import wp.eshop.emtlab.model.enumerations.BookCategory;
import wp.eshop.emtlab.service.BookService;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> findAll() {
        return this.bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return this.bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto) {
        return this.bookService.create(bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> edit(@PathVariable Long id, @RequestBody BookDto bookDto) {
        return this.bookService.update(id, bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.bookService.delete(id);
        if(this.bookService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("/reserve/{id}")
    public ResponseEntity<Book> reserveBooks(@PathVariable Long id, @RequestParam Integer numReserved) {
        return this.bookService.reserveBooks(id, numReserved)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @PostMapping("/return/{id}")
    public ResponseEntity<Book> returnBooks(@PathVariable Long id, @RequestParam Integer numReturned) {
        return this.bookService.returnBooks(id, numReturned)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @GetMapping("/categories")
    public List<BookCategory> listAllCategories() {
        return Arrays.stream(BookCategory.values()).toList();
    }
}
