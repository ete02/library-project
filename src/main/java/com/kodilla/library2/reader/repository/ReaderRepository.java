package com.kodilla.library2.reader.repository;

import com.kodilla.library2.reader.domain.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface ReaderRepository extends CrudRepository<Reader, Long> {
    @Override
    Reader save(Reader reader);

    @Override
    Optional<Reader> findById(Long id);

    @Override
    List<Reader> findAll();

    @Override
    void deleteById(Long id);

    @Override
    void deleteAll();
}