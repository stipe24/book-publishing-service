package com.infinum.bookpublishingservice.model.book;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
public class BookRequest {

    @NotNull
    private String title;
    @NotNull
    private String isbn;
    @NotNull
    private Instant publishedAt;
    @NotEmpty
    private List<String> authorIds;
    @NotEmpty
    private List<String> genreIds;
}