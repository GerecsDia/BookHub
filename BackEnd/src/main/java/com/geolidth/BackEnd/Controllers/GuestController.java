package com.geolidth.BackEnd.Controllers;

import com.geolidth.BackEnd.exceptions.NoSuchBookException;
import com.geolidth.BackEnd.models.dao.Book;
import com.geolidth.BackEnd.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/guest")
public class GuestController {
    private final BookService bookService;

    public GuestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/books/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam(required = false) String title,
                                                  @RequestParam(required = false) String author) {
        try {
            List<Book> books;
            if (title != null && author != null) {
                books = bookService.searchBooksByTitleAndAuthor(title, author);
            } else if (title != null) {
                books = bookService.searchBooksByTitle(title);
            } else if (author != null) {
                books = bookService.searchBooksByAuthor(author);
            } else {
                books = Collections.emptyList();
            }
            return ResponseEntity.ok(books);
        } catch (NoSuchBookException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/reserve")
    public ResponseEntity<String> reserveBook() {
        String message = "Bejelentkezés szükséges a könyv lefoglalásához.";
        message += "Ha még nem regisztrált, akkor regisztráljon a /api/v1/auth/register végponton keresztül";
        message += "vagy jelentkezzen be a /api/v1/auth/login végponton.";
        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }
}





