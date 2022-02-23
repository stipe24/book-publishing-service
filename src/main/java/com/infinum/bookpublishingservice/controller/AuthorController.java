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

@RestController
@AllArgsConstructor
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    //TODO: Create author - ADMIN ONLY!
    @PostMapping
    public ResponseEntity<Author> create(@RequestBody AuthorRequest request) {
        return new ResponseEntity<>(authorService.create(request), HttpStatus.CREATED);
    }

    //TODO: Update author - ADMIN ONLY!
    @PatchMapping("/{authorId}")
    public ResponseEntity<Author> update(@PathVariable String authorId, @RequestBody AuthorRequest request) {
        return new ResponseEntity<>(authorService.update(authorId, request.getName()), HttpStatus.OK);
    }

    //TODO: PUBLIC!
    @GetMapping
    public ResponseEntity<Page<AuthorCount>> get(AuthorPage page) {
        return new ResponseEntity<>(authorService.count(page), HttpStatus.OK);
    }

}