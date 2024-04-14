package com.geolidth.BackEnd.repositories;

import com.geolidth.BackEnd.models.dao.BookUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookUserRepository extends JpaRepository<BookUser, Integer> {
    Optional<BookUser> findByUsername(String username);
}