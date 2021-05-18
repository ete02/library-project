package com.kodilla.library2.borrow.service;

import com.kodilla.library2.borrow.domain.Borrow;
import com.kodilla.library2.borrow.repository.BorrowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public final class BorrowServiceDb {
    private final BorrowRepository borrowRepository;

    public Borrow saveBorrow(final Borrow borrow) {
        return borrowRepository.save(borrow);
    }

    public Optional<Borrow> getBorrowById(final Long id) {
        return borrowRepository.findById(id);
    }

    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    public void deleteBorrowById(final Long id) {
        borrowRepository.deleteById(id);
    }

    public void deleteAllBorrows() {
        borrowRepository.deleteAll();
    }
}