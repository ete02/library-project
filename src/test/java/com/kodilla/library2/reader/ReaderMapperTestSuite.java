package com.kodilla.library2.reader;

import com.kodilla.library2.database.DataBase;
import com.kodilla.library2.reader.domain.Reader;
import com.kodilla.library2.reader.domain.ReaderDto;
import com.kodilla.library2.reader.mapper.ReaderMapper;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReaderMapperTestSuite {
    private DataBase dataBase;

    @Autowired
    private ReaderMapper readerMapper;

    @BeforeEach
    public void setUp() {
        dataBase = new DataBase();
    }

    @Test
    public void shouldMapToReader() {
        //GIVEN
        ReaderDto readerDto = dataBase.getReaderDto();
        long readerDtoId = readerDto.getId();
        String readerNameDto = readerDto.getName();
        String readerSurnameDto = readerDto.getLastname();
        //WHEN
        Reader reader = readerMapper.mapToReader(readerDto);
        long readerId = reader.getId();
        String readerName = reader.getName();
        String readerSurname = reader.getLastname();
        //THEN
        assertEquals(readerDtoId, readerId);
        assertEquals(readerNameDto, readerName);
        assertEquals(readerSurnameDto, readerSurname);
    }

    @Test
    public void shouldMapToReaderDto() {
        //GIVEN
        Reader reader = dataBase.getReader();
        Long readerId = reader.getId();
        String readerName = reader.getName();
        String readerSurname = reader.getLastname();
        Date readerCreateAccountDate = reader.getCreateAccountDate();
        //WHEN
        ReaderDto readerDto = readerMapper.mapToReaderDto(reader);
        Long readerDtoId = readerDto.getId();
        String readerNameDto = readerDto.getName();
        String readerSurnameDto = readerDto.getLastname();
        Date readerCreateAccountDateDto = readerDto.getCreateAccountDate();
        //THEN
        assertEquals(readerId, readerDtoId);
        assertEquals(readerName, readerNameDto);
        assertEquals(readerSurname, readerSurnameDto);
        assertEquals(readerCreateAccountDate, readerCreateAccountDateDto);
    }

    @Test
    public void shouldMapToReadersDto() {
        //GIVEN
        Reader reader = dataBase.getReader();
        Date createAccountDate = reader.getCreateAccountDate();
        List<Reader> readers = new ArrayList<>();
        readers.add(reader);
        //WHEN
        List<ReaderDto> readersDto = readerMapper.mapToReadersDto(readers);
        //THEN
        assertEquals(1, readersDto.size());
        assertNull(readersDto.get(0).getId());
        assertEquals("Name", readersDto.get(0).getName());
        assertEquals("Lastname", readersDto.get(0).getLastname());
        assertEquals(createAccountDate, readersDto.get(0).getCreateAccountDate());
    }
}
