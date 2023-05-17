package wp.eshop.emtlab.web;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wp.eshop.emtlab.model.Author;
import wp.eshop.emtlab.model.Book;
import wp.eshop.emtlab.model.dto.AuthorDto;
import wp.eshop.emtlab.model.dto.BookDto;
import wp.eshop.emtlab.service.AuthorService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> findAll() {
        return this.authorService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Author> create(@RequestBody AuthorDto authorDto) {
        return authorService.create(authorDto)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Author> edit(@PathVariable Long id, @RequestBody AuthorDto authorDto) {
        return this.authorService.update(id, authorDto)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            authorService.delete(id);
        } catch (EmptyResultDataAccessException ex) {
            return ResponseEntity.badRequest().build();
        }
        if (authorService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.internalServerError().build();
    }
    //comment
}
