package com.infinum.bookpublishingservice.controller;

import com.infinum.bookpublishingservice.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
}