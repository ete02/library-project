package com.kodilla.library2.book.repository;

import com.kodilla.library2.book.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    @Override
    Book save(Book book);

    @Override
    Optional<Book> findById(Long id);

    @Override
    List<Book> findAll();

    @Override
    void deleteById(Long id);

    @Override
    void deleteAll();
}
