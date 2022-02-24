package com.infinum.bookpublishingservice.controller;

import com.infinum.bookpublishingservice.model.book.BookFilter;
import com.infinum.bookpublishingservice.model.book.BookPage;
import com.infinum.bookpublishingservice.model.book.BookRequest;
import com.infinum.bookpublishingservice.model.entity.BookEntity;
import com.infinum.bookpublishingservice.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookEntity> create(@RequestBody @Valid BookRequest request) {
        return new ResponseEntity<>(bookService.create(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<BookEntity>> get(BookFilter filter, BookPage page) {
        var bookFilter = filter != null ? filter : new BookFilter();
        return new ResponseEntity<>(bookService.findByFilter(bookFilter, page), HttpStatus.OK);
    }

}