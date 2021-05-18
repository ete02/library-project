package com.kodilla.library2.title.service;

import com.kodilla.library2.title.domain.Title;
import com.kodilla.library2.title.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TitleServiceDb {
    private final TitleRepository titleRepository;

    public Title saveTitle(final Title title) {
        return titleRepository.save(title);
    }

    public Optional<Title> getTitleById(final Long id) {
        return titleRepository.findById(id);
    }

    public List<Title> getAllTitles() {
        return titleRepository.findAll();
    }

    public void deleteTitleById(final Long id) {
        titleRepository.deleteById(id);
    }

    public void deleteAllTitles() {
        titleRepository.deleteAll();
    }
}
