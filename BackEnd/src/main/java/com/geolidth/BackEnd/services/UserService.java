package com.geolidth.BackEnd.services;

import com.geolidth.BackEnd.models.dao.Book;
import com.geolidth.BackEnd.models.dao.BookUser;
import com.geolidth.BackEnd.models.dto.NewUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService extends UserDetailsService {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    BookUser save(BookUser newUser);

    BookUser getById(Integer id);

    Book addBook(Book book);

    BookUser updateUser(Integer userId, NewUser userDetails);

    void deleteUser(Integer userId);

    void deleteUserData(Integer userId);

    void reserveBook(Long bookId, Integer userId);

    BookUser findUserByUsername(String username);

    BookUser findUserById(Integer userId);

    List<BookUser> getAllUsers();

    boolean existsByUsername(String username);

}
