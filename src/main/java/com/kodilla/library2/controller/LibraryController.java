package com.kodilla.library2.controller;

import com.kodilla.library2.book.domain.BookDto;
import com.kodilla.library2.book.mapper.BookMapper;
import com.kodilla.library2.borrow.controller.CanNotBeExecutedException;
import com.kodilla.library2.borrow.domain.BorrowDto;
import com.kodilla.library2.borrow.mapper.BorrowMapper;
import com.kodilla.library2.facade.LibraryFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/library")
public final class LibraryController {
    private final BorrowMapper borrowMapper;
    private final BookMapper bookMapper;
    private final LibraryFacade libraryFacade;

    @GetMapping("/books")
    public List<BookDto> getAvailableBooks(@RequestParam final Long titleId) {
        log.info("Get available books by titleId: " + titleId);
        return bookMapper.mapToBooksDto(libraryFacade.findAvailableBooks(titleId));
    }

    @GetMapping("/borrows")
    public List<BorrowDto> getBorrowedBookByReader(@RequestParam final Long readerId) {
        log.info("Get reader borrows by readerId: " + readerId);
        return borrowMapper.mapToBorrowsDto(libraryFacade.findBorrowedBookByReader(readerId));
    }

    @PostMapping("/borrow")
    public void borrowBook(@RequestParam final Long titleId, @RequestParam final Long readerId) throws CanNotBeExecutedException {
        log.info("Borrow book by titleId: " + titleId + " and " + "readerID: " + readerId);
        libraryFacade.executeBorrow(titleId, readerId);
    }

    @PutMapping("/return")
    public BorrowDto returnBook(@RequestParam final Long bookId, @RequestParam final Long readerId) throws CanNotBeExecutedException {
        log.info("Return book by bookId: " + bookId + " and " + "readerID: " + readerId);
        return borrowMapper.mapToBorrowDto(libraryFacade.executeReturn(bookId, readerId));
    }
}