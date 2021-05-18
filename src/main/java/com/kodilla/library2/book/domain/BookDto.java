package com.kodilla.library2.book.domain;

import com.kodilla.library2.title.domain.TitleDto;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public final class BookDto {
    private final Long id;
    private final TitleDto title;
    private final BookStatus bookStatus;
}