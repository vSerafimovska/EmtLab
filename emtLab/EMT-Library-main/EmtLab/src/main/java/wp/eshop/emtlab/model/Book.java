package wp.eshop.emtlab.model;

import jakarta.persistence.*;
import lombok.Data;
import wp.eshop.emtlab.model.enumerations.BookCategory;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private BookCategory bookCategory;

    @ManyToOne
    private Author author;

    private Integer availableCopies;

    public Book() {
    }

    public Book(String name, BookCategory bookCategory, Author author, Integer availableCopies) {
        this.name = name;
        this.bookCategory = bookCategory;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
