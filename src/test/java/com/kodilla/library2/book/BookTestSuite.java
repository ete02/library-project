package com.kodilla.library2.book;

import com.kodilla.library2.book.domain.Book;
import com.kodilla.library2.book.domain.BookDto;
import com.kodilla.library2.book.domain.BookStatus;
import com.kodilla.library2.book.mapper.BookMapper;
import com.kodilla.library2.database.DataBase;
import com.kodilla.library2.title.domain.Title;
import com.kodilla.library2.title.domain.TitleDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.kodilla.library2.book.domain.BookStatus.IN_LIBRARY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookTestSuite {
    private DataBase dataBase;
    @Autowired
    private BookMapper bookMapper;

    @Before
    public void setUp() {
        dataBase = new DataBase();
    }

    @Test
    public void shouldMapToBook() {
        //GIVEN
        BookDto bookDto = dataBase.getBookDto(dataBase.getTitleDto(), IN_LIBRARY);
        long bookDtoId = bookDto.getId();
        TitleDto titleDto = bookDto.getTitle();
        BookStatus bookStatusDto = bookDto.getBookStatus();
        //WHEN
        Book book = bookMapper.mapToBook(bookDto);
        long bookIdAfterMapping = book.getId();
        Title titleAfterMapping = book.getTitle();
        BookStatus bookStatusAfterMapping = book.getBookStatus();
        //THEN
        assertEquals(bookDtoId, bookIdAfterMapping);
        assertEquals(titleDto.getId(), titleAfterMapping.getId());
        assertEquals(titleDto.getAuthor(), titleAfterMapping.getAuthor());
        assertEquals(titleDto.getTitle(), titleAfterMapping.getTitle());
        assertEquals(titleDto.getPublishedYear(), titleAfterMapping.getPublishedYear());
        assertEquals(bookStatusDto, bookStatusAfterMapping);
    }

    @Test
    public void shouldMapToBookDto() {
        //GIVEN
        Book book = dataBase.getBook(dataBase.getTitle(), IN_LIBRARY);
        Long bookId = book.getId();
        Title title = book.getTitle();
        BookStatus bookStatus = book.getBookStatus();
        //WHEN
        BookDto bookDto = bookMapper.mapToBookDto(book);
        Long bookDtoIdAfterMapping = bookDto.getId();
        TitleDto titleDtoAfterMapping = bookDto.getTitle();
        BookStatus bookStatusDtoAfterMapping = bookDto.getBookStatus();
        //THEN
        assertEquals(bookId, bookDtoIdAfterMapping);
        assertEquals(title.getId(), titleDtoAfterMapping.getId());
        assertEquals(title.getAuthor(), titleDtoAfterMapping.getAuthor());
        assertEquals(title.getTitle(), titleDtoAfterMapping.getTitle());
        assertEquals(title.getPublishedYear(), titleDtoAfterMapping.getPublishedYear());
        assertEquals(bookStatus, bookStatusDtoAfterMapping);
    }

    @Test
    public void shouldMapToBooksDto() {
        //GIVEN
        Book book = dataBase.getBook(dataBase.getTitle(), IN_LIBRARY);
        List<Book> books = new ArrayList<>();
        books.add(book);
        //WHEN
        List<BookDto> booksDto = bookMapper.mapToBooksDto(books);
        //THEN
        assertEquals(1, booksDto.size());
        assertNull(booksDto.get(0).getId());
        assertEquals(IN_LIBRARY, booksDto.get(0).getBookStatus());
        assertNull(booksDto.get(0).getTitle().getId());
        assertEquals("Author", booksDto.get(0).getTitle().getAuthor());
        assertEquals("Title", booksDto.get(0).getTitle().getTitle());
        assertEquals(LocalDate.now(), booksDto.get(0).getTitle().getPublishedYear());
    }
}
