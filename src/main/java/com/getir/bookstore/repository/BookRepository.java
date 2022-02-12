package com.getir.bookstore.repository;

import com.getir.bookstore.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {

    boolean existsByNameAndAuthor(String name, String author);
    Optional<Book> findBookById(int id);

}
