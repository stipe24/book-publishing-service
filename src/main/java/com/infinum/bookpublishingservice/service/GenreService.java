package com.infinum.bookpublishingservice.service;

import com.infinum.bookpublishingservice.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;
}