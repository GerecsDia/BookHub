package com.geolidth.BackEnd.Controllers;

import com.geolidth.BackEnd.exceptions.ForbiddenActionException;
import com.geolidth.BackEnd.exceptions.NoSuchBookException;
import com.geolidth.BackEnd.exceptions.NoSuchUserException;
import com.geolidth.BackEnd.models.dao.Book;
import com.geolidth.BackEnd.models.dao.BookUser;
import com.geolidth.BackEnd.models.dto.NewBook;
import com.geolidth.BackEnd.models.dto.NewUser;
import com.geolidth.BackEnd.models.dto.UpdateBook;
import com.geolidth.BackEnd.services.BookService;
import com.geolidth.BackEnd.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    private final UserService userService;
    private final BookService bookService;

    public AdminController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }
    @GetMapping("/admin/all")
    public ResponseEntity<List<BookUser>> getAllUsers() {
        List<BookUser> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
        try {
            Book book = bookService.getById(id);
            return ResponseEntity.ok(book);
        } catch (NoSuchBookException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/admin/username/{username}")
    public ResponseEntity<?> findUserByUsername(@PathVariable String username) {
        try {
            BookUser user = userService.findUserByUsername(username);
            if (user != null) {
                return ResponseEntity.status(HttpStatus.OK).body(user);
            } else {
                throw new NoSuchUserException("Nincs ilyen felhasználó: " + username);
            }
        } catch (NoSuchUserException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/admin/{userId}")
    public ResponseEntity<BookUser> findUserById(@PathVariable Integer userId) {
        BookUser user = userService.getById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody NewUser newUser) {
        BookUser savedUser = userService.save(new BookUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
    @PostMapping("/books")
    public ResponseEntity<?> createBook(@RequestBody NewBook newBook) {
        Book savedBook = bookService.save(newBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }
    @PutMapping("/users/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Integer userId, @RequestBody NewUser userDetails) {
        BookUser updatedUser = userService.updateUser(userId, userDetails);
        return ResponseEntity.ok(updatedUser);
    }
    @PutMapping("/books/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer bookId, @RequestBody UpdateBook updateBook) {
        try {
            Book updatedBook = bookService.updateBook(bookId, updateBook);
            return ResponseEntity.ok(updatedBook);
        } catch (NoSuchBookException | ForbiddenActionException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer bookId) {
        try {
            bookService.deleteBook(bookId);
            return ResponseEntity.noContent().build();
        } catch (NoSuchBookException | ForbiddenActionException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/books/{bookId}/reserve")
    public ResponseEntity<Void> reserveBook(@PathVariable Integer bookId) {
        try {
            Book book = bookService.getById(bookId);
            book.setReserved(true);
            UpdateBook updateBook = new UpdateBook();
            updateBook.setReserved(true);
            bookService.updateBook(bookId, updateBook);
            bookService.deleteBook(bookId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (NoSuchBookException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
