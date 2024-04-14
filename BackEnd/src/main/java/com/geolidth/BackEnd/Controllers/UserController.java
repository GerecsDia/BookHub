package com.geolidth.BackEnd.Controllers;

import com.geolidth.BackEnd.exceptions.ForbiddenActionException;
import com.geolidth.BackEnd.exceptions.NoSuchBookException;
import com.geolidth.BackEnd.models.dao.Book;
import com.geolidth.BackEnd.models.dao.BookUser;
import com.geolidth.BackEnd.models.dto.NewUser;
import com.geolidth.BackEnd.models.dto.UpdateBook;
import com.geolidth.BackEnd.services.BookService;
import com.geolidth.BackEnd.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final BookService bookService;

    public UserController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping("/user/profile")
    public ResponseEntity<?> getUserProfile() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        BookUser user = userService.findUserByUsername(username);
        return ResponseEntity.ok(user);
    }
    /*@PostMapping("/signup")
    public ResponseEntity<BookUser> signUp(@RequestBody NewUser newUserRequest) {
        BookUser savedUser = userService.save(new BookUser(newUserRequest));
        return ResponseEntity.status(CREATED).body(savedUser);
    }*/

    @PostMapping("/users/book")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addBook(book));
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<BookUser> updateUser(@PathVariable Integer userId,
                                               @RequestBody NewUser userDetails) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        BookUser currentUser = (BookUser) authentication.getPrincipal();
        if (!Objects.equals(currentUser.getId(), userId)) {
            throw new ForbiddenActionException("Nincs jogosultsága a felhasználó módosításához");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.updateUser(userId, userDetails));
    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer bookId, @RequestBody UpdateBook updateBook) {
        UserDetails currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUsername = currentUser.getUsername();

        try {
            Book book = bookService.getById(bookId);
            if (!book.getOwner().getUsername().equals(currentUsername)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            Book updatedBook = bookService.updateBook(bookId, updateBook);
            return ResponseEntity.ok(updatedBook);
        } catch (NoSuchBookException | ForbiddenActionException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUserData(@PathVariable Integer userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        BookUser currentUser = (BookUser) authentication.getPrincipal();
        if (!Objects.equals(currentUser.getId(), userId)) {
            throw new ForbiddenActionException("Nincs jogosultsága a felhasználó törléséhez");
        }
        userService.deleteUserData(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer bookId) {
        UserDetails currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUsername = currentUser.getUsername();

        try {
            Book book = bookService.getById(bookId);
            if (!book.getOwner().getUsername().equals(currentUsername)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            bookService.deleteBook(bookId);
            return ResponseEntity.noContent().build();
        } catch (NoSuchBookException e) {
            return ResponseEntity.notFound().build();
        } catch (ForbiddenActionException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
    @PostMapping("/reserve/{bookId}")
    public ResponseEntity<Void> reserveBook(@PathVariable Long bookId,
            Authentication authentication) {
        userService.reserveBook(bookId, ((BookUser) authentication.getPrincipal()).getId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}