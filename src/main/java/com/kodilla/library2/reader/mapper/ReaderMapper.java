package com.kodilla.library2.reader.mapper;

import com.kodilla.library2.reader.domain.ReaderDto;
import org.springframework.stereotype.Component;

import com.kodilla.library2.reader.domain.Reader;
import java.util.List;
import java.util.stream.Collectors;

@Component
public final class ReaderMapper {
    public Reader mapToReader(final ReaderDto readerDto) {
        return Reader.builder()
                .id(readerDto.getId())
                .name(readerDto.getName())
                .lastname(readerDto.getLastname())
                .build();
    }

    public ReaderDto mapToReaderDto(final Reader reader) {
        return ReaderDto.builder()
                .id(reader.getId())
                .name(reader.getName())
                .lastname(reader.getLastname())
                .createAccountDate(reader.getCreateAccountDate())
                .build();
    }

    public List<ReaderDto> mapToReadersDto(final List<Reader> readers) {
        return readers.stream()
                .map(reader -> ReaderDto.builder()
                        .id(reader.getId())
                        .name(reader.getName())
                        .lastname(reader.getLastname())
                        .createAccountDate(reader.getCreateAccountDate())
                        .build())
                .collect(Collectors.toList());
    }
}
