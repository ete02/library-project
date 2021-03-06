package com.kodilla.library2.book.mapper;

import com.kodilla.library2.book.domain.Book;
import com.kodilla.library2.book.domain.BookDto;
import com.kodilla.library2.title.domain.TitleDto;
import com.kodilla.library2.title.mapper.TitleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public final class BookMapper {
    private final TitleMapper titleMapper;

    public Book mapToBook(final BookDto bookDto) {
        return Book.builder()
                .id(bookDto.getId())
                .title(titleMapper.mapToTitle(bookDto.getTitle()))
                .bookStatus(bookDto.getBookStatus())
                .build();
    }

    public BookDto mapToBookDto(final Book book) {
        return BookDto.builder()
                .id(book.getId())
                .title(titleMapper.mapToTitleDto(book.getTitle()))
                .bookStatus(book.getBookStatus())
                .build();
    }

    public List<BookDto> mapToBooksDto(final List<Book> books) {
        return books.stream()
                .map(book -> BookDto.builder()
                        .id(book.getId())
                        .title(titleMapper.mapToTitleDto(book.getTitle()))
                        .bookStatus(book.getBookStatus())
                        .build())
                .collect(Collectors.toList());
    }
}
