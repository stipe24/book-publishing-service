package com.infinum.bookpublishingservice.controller;

import com.infinum.bookpublishingservice.model.author.Author;
import com.infinum.bookpublishingservice.model.author.AuthorCount;
import com.infinum.bookpublishingservice.model.author.AuthorPage;
import com.infinum.bookpublishingservice.model.author.AuthorRequest;
import com.infinum.bookpublishingservice.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<Author> create(@RequestBody @Valid AuthorRequest request) {
        return new ResponseEntity<>(authorService.create(request), HttpStatus.CREATED);
    }

    @PatchMapping("/{authorId}")
    public ResponseEntity<Author> update(@PathVariable String authorId, @RequestBody @Valid AuthorRequest request) {
        return new ResponseEntity<>(authorService.update(authorId, request.getName()), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<AuthorCount>> get(AuthorPage page) {
        return new ResponseEntity<>(authorService.get(page), HttpStatus.OK);
    }

}