package com.infinum.bookpublishingservice.model.book;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
public class BookRequest {

    private String title;

    private String isbn;

    private Instant publishedAt;

    private List<String> authorIds;

    private List<String> genreIds;
}