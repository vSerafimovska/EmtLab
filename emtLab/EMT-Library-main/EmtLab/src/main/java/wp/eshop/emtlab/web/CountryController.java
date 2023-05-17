package wp.eshop.emtlab.web;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wp.eshop.emtlab.model.Author;
import wp.eshop.emtlab.model.Country;
import wp.eshop.emtlab.model.dto.CountryDto;
import wp.eshop.emtlab.service.CountryService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/countries")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> findAll() {
        return this.countryService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Country> create(@RequestBody CountryDto countryDto) {
        try {
            return countryService.create(countryDto)
                    .map(country -> ResponseEntity.ok().body(country))
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            countryService.delete(id);
        } catch (EmptyResultDataAccessException ex) {
            return ResponseEntity.badRequest().build();
        }
        if (countryService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.internalServerError().build();
    }
}
