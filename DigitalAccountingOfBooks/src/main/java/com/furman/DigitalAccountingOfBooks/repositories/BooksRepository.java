package com.furman.DigitalAccountingOfBooks.repositories;

import com.furman.DigitalAccountingOfBooks.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByTitleStartingWith(String title);
}
