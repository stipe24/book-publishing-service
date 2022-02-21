package com.infinum.bookpublishingservice.controller;

import com.infinum.bookpublishingservice.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;
}