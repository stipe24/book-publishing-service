package com.infinum.bookpublishingservice.service;

import com.infinum.bookpublishingservice.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
}