package com.geolidth.BackEnd.repositories;

import com.geolidth.BackEnd.models.dao.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
    List<Book> findAll();

    List<Book> findAllByAuthorContainsIgnoreCase(String q);

    List<Book> findAllByTitleContainsIgnoreCase(String q);

    List<Book> findAllByPublisherContainsIgnoreCase(String q);

    List<Book> findAllByCategoryContainsIgnoreCase(String q);

    List<Book> findAllByCountyContainsIgnoreCase(String q);

    List<Book> findAllByQualityContainsIgnoreCase(String q);

    Optional<Book> findById(Integer id);

    @Query("SELECT b FROM Book b WHERE b.year = :year")
    List<Book> findAllByYear(@Param("year") int year);
}
