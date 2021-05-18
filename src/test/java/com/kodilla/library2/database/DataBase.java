package com.kodilla.library2.database;

import com.kodilla.library2.book.domain.Book;
import com.kodilla.library2.book.domain.BookDto;
import com.kodilla.library2.book.domain.BookStatus;
import com.kodilla.library2.borrow.domain.Borrow;
import com.kodilla.library2.borrow.domain.BorrowDto;
import com.kodilla.library2.reader.domain.Reader;
import com.kodilla.library2.reader.domain.ReaderDto;
import com.kodilla.library2.title.domain.Title;
import com.kodilla.library2.title.domain.TitleDto;

import java.time.LocalDate;
import java.util.Date;

public final class DataBase {
    public Title getTitle() {
        return Title.builder()
                .title("Title")
                .author("Author")
                .publishedYear(LocalDate.now())
                .build();
    }

    public Book getBook(Title title, BookStatus bookStatus) {
        return Book.builder()
                .title(title)
                .bookStatus(bookStatus)
                .build();
    }

    public Reader getReader() {
        return Reader.builder()
                .name("Name")
                .lastname("Lastname")
                .build();
    }

    public Borrow getBorrow(Book book, Reader reader) {
        return Borrow.builder()
                .borrowDate(LocalDate.now().minusMonths(1))
                .book(book)
                .reader(reader)
                .build();
    }

    public TitleDto getTitleDto() {
        return TitleDto.builder()
                .id(1L)
                .title("Title")
                .author("Author")
                .publishedYear(LocalDate.now())
                .build();
    }

    public BookDto getBookDto(TitleDto title, BookStatus bookStatus) {
        return BookDto.builder()
                .id(1L)
                .title(title)
                .bookStatus(bookStatus)
                .build();
    }

    public ReaderDto getReaderDto() {
        return ReaderDto.builder()
                .id(1L)
                .name("Name")
                .lastname("Lastname")
                .createAccountDate(new Date())
                .build();
    }

    public BorrowDto getBorrowDto(BookDto book, ReaderDto reader) {
        return BorrowDto.builder()
                .id(1L)
                .borrowDate(LocalDate.now().minusMonths(1))
                .book(book)
                .reader(reader)
                .build();
    }
}