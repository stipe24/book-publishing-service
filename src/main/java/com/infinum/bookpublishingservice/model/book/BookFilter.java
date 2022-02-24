package com.infinum.bookpublishingservice.model.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookFilter {

    private String title;
    private String isbn;
    private Set<String> genreIds;
    private Set<String> authorIds;
    private Date addedAt;
}