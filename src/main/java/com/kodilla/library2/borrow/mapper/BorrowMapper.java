package com.kodilla.library2.borrow.mapper;

import com.kodilla.library2.book.mapper.BookMapper;
import com.kodilla.library2.borrow.domain.Borrow;
import com.kodilla.library2.borrow.domain.BorrowDto;
import com.kodilla.library2.reader.domain.ReaderDto;
import com.kodilla.library2.reader.mapper.ReaderMapper;
import com.kodilla.library2.reader.domain.Reader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public final class BorrowMapper {
    private final BookMapper bookMapper;
    private final ReaderMapper readerMapper;

    public Borrow mapToBorrow (final BorrowDto borrowDto) {
        return Borrow.builder()
                .id(borrowDto.getId())
                .reader(readerMapper.mapToReader(borrowDto.getReader())
                .book(bookMapper.mapToBook(borrowDto.getBook()))
                .borrowDate(borrowDto.getBorrowDate())
                .returnDate(borrowDto.getReturnDate())
                .build();
    }

    public BorrowDto mapToBorrowDto(final Borrow borrow) {
        return BorrowDto.builder()
                .id(borrow.getId())
                .reader(readerMapper.mapToReaderDto(borrow.getReader()))
                .book(bookMapper.mapToBookDto(borrow.getBook()))
                .borrowDate(borrow.getBorrowDate())
                .returnDate(borrow.getReturnDate())
                .build();
    }

    public List<BorrowDto> mapToBorrowsDto(final List<Borrow> borrows) {
        return borrows.stream()
                .map(borrow -> BorrowDto.builder()
                        .id(borrow.getId())
                        .reader(readerMapper.mapToReaderDto(borrow.getReader()))
                        .book(bookMapper.mapToBookDto(borrow.getBook()))
                        .borrowDate(borrow.getBorrowDate())
                        .returnDate(borrow.getReturnDate())
                        .build())
                .collect(Collectors.toList());
    }
}