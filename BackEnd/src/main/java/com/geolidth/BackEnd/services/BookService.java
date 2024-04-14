package com.geolidth.BackEnd.services;

import com.geolidth.BackEnd.exceptions.ForbiddenActionException;
import com.geolidth.BackEnd.exceptions.NoSuchBookException;
import com.geolidth.BackEnd.exceptions.NoSuchUserException;
import com.geolidth.BackEnd.models.dao.Book;
import com.geolidth.BackEnd.models.dto.NewBook;
import com.geolidth.BackEnd.models.dto.UpdateBook;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getBooks();
    List<Book> getBooks(Optional<String> q);
    List<Book> searchBooks(String query);
    List<Book> searchBooksByTitle(String title);
    List<Book> searchBooksByAuthor(String author);
    List<Book> searchBooksByTitleAndAuthor(String title, String author);
    Book getById(int id)
            throws NoSuchBookException;
    Book save(NewBook newBook)
            throws NoSuchUserException;
    Book updateBook(Integer bookId, UpdateBook updateBook)
            throws NoSuchUserException, NoSuchBookException, ForbiddenActionException;
    void deleteBook(Integer bookId)
            throws NoSuchUserException, NoSuchBookException, ForbiddenActionException;
    void reserveBook(Integer bookId)
            throws NoSuchBookException;



}
