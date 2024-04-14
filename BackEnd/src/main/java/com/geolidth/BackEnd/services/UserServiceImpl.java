package com.geolidth.BackEnd.services;

import com.geolidth.BackEnd.exceptions.NoSuchUserException;
import com.geolidth.BackEnd.models.dao.Book;
import com.geolidth.BackEnd.models.dao.BookUser;
import com.geolidth.BackEnd.models.dto.NewUser;
import com.geolidth.BackEnd.repositories.BookUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private final BookUserRepository bookUserRepository;


    public UserServiceImpl(BookUserRepository bookUserRepository) {

        this.bookUserRepository = bookUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return bookUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Nem található ilyen felhasználó: " + username));
    }

    @Override
    @Transactional
    public BookUser save(BookUser newUser) {
        BookUser bookUser = new BookUser();
        bookUser.setUsername(newUser.getUsername());
        bookUser.setPassword(newUser.getPassword());
        bookUser.setEmail(newUser.getEmail());
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");
        return bookUserRepository.save(bookUser);
    }


    @Override
    public BookUser getById(Integer id) {
        return bookUserRepository.findById(id)
                .orElseThrow(() -> new NoSuchUserException(id));
    }

    @Override
    public Book addBook(Book book) {
        return null;
    }

    @Override
    public void reserveBook(Long bookId, Integer userId) {

    }

    @Override
    public BookUser updateUser(Integer userId, NewUser userDetails) {
        BookUser existingUser = getById(userId);

        existingUser.setUsername(userDetails.getUsername());
        existingUser.setPassword(userDetails.getPassword());
        existingUser.setEmail(userDetails.getEmail());

        return bookUserRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Integer userId) {
        bookUserRepository.deleteById(userId);
    }

    @Override
    public void deleteUserData(Integer userId) {

    }

    @Override
    public BookUser findUserByUsername(String username) {
        return bookUserRepository.findByUsername(username)
                .orElse(null);
    }

    @Override
    public BookUser findUserById(Integer userId) {
        return null;
    }

    @Override
    public List<BookUser> getAllUsers() {
        return bookUserRepository.findAll();
    }


    @Override
    public boolean existsByUsername(String username) {
        return bookUserRepository.findByUsername(username).isPresent();
    }


}