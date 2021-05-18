package com.kodilla.library2.reader.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Builder
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public final class ReaderDto {
    private final Long id;
    private final String name;
    private final String lastname;
    private final Date createAccountDate;
}