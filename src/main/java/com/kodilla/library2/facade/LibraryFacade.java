package com.kodilla.library2.facade;

import com.kodilla.library2.book.domain.Book;
import com.kodilla.library2.book.domain.BookStatus;
import com.kodilla.library2.book.service.BookServiceDb;
import com.kodilla.library2.borrow.controller.CanNotBeExecutedException;
import com.kodilla.library2.borrow.domain.Borrow;
import com.kodilla.library2.borrow.service.BorrowServiceDb;
import com.kodilla.library2.reader.domain.Reader;
import com.kodilla.library2.reader.service.ReaderServiceDb;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.kodilla.library2.book.domain.BookStatus.*;

@Service
@RequiredArgsConstructor
public final class LibraryFacade {
    private final BookServiceDb bookServiceDb;
    private final BorrowServiceDb borrowServiceDb;
    private final ReaderServiceDb readerServiceDb;

    public List<Book> findAvailableBooks(final Long titleId) {
        return bookServiceDb.getAllBooks().stream()
                .filter(book -> book.getTitle().getId().equals(titleId))
                .filter(book -> book.getBookStatus() == IN_LIBRARY)
                .collect(Collectors.toList());
    }

    public List<Borrow> findBorrowedBookByReader(final Long readerId) {
        return borrowServiceDb.getAllBorrows().stream()
                .filter(borrow -> borrow.getReader().equals(readerId))
                .filter(borrow -> borrow.getReturnDate() == null)
                .filter(borrow -> borrow.getBook().getBookStatus() == IN_CIRCULATION || borrow.getBook().getBookStatus() == LOST)
                .collect(Collectors.toList());
    }

    public void executeBorrow(final Long titleId, final Long readerId) throws CanNotBeExecutedException {
        List<Book> books = findAvailableBooks(titleId);
        Optional<Reader> reader = readerServiceDb.getReaderById(readerId);
        if (!books.isEmpty() && reader.isPresent()) {
            Borrow borrow = updateBorrowedBook(updateBookStatus(books.get(0), IN_CIRCULATION), reader.get());
            borrowServiceDb.saveBorrow(borrow);
        } else {
            throw new CanNotBeExecutedException("Borrow can not be executed! - Reader or Book not found.");
        }
    }

    public Borrow executeReturn(Long bookId, Long readerId) throws CanNotBeExecutedException {
        List<Borrow> borrows = findBorrowedBook(bookId, readerId);
        Optional<Reader> reader = readerServiceDb.getReaderById(readerId);
        if (!borrows.isEmpty() && reader.isPresent()) {
            Borrow borrow = updateReturnedBook(updateBookStatus(borrows.get(0).getBook(), IN_LIBRARY), borrows.get(0));
            return borrowServiceDb.saveBorrow(borrow);
        } else {
            throw new CanNotBeExecutedException("Return can not be executed! - Reader or Book not found.");
        }
    }

    private List<Borrow> findBorrowedBook(final Long bookId, final Long readerId) {
        return borrowServiceDb.getAllBorrows().stream()
                .filter(borrow -> borrow.getReader().equals(readerId))
                .filter(borrow -> borrow.getBook().getId().equals(bookId))
                .filter(borrow -> borrow.getReturnDate() == null)
                .filter(borrow -> borrow.getBook().getBookStatus() == IN_CIRCULATION || borrow.getBook().getBookStatus() == LOST)
                .collect(Collectors.toList());
    }

    private Book updateBookStatus(final Book book, final BookStatus bookStatus) {
        return bookServiceDb.saveBook(Book.builder()
                .id(book.getId())
                .title(book.getTitle())
                .bookStatus(bookStatus)
                .borrows(book.getBorrows())
                .build());
    }

    private Borrow updateBorrowedBook(final Book book, final Reader reader) {
        return Borrow.builder()
                .reader(reader)
                .book(book)
                .borrowDate(LocalDate.now())
                .build();
    }

    private Borrow updateReturnedBook(final Book book, final Borrow borrow) {
        return Borrow.builder()
                .id(borrow.getId())
                .reader(borrow.getReader())
                .book(book)
                .borrowDate(borrow.getBorrowDate())
                .returnDate(LocalDate.now())
                .build();
    }
}
