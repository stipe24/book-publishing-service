package com.infinum.bookpublishingservice.service;

import com.infinum.bookpublishingservice.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
}