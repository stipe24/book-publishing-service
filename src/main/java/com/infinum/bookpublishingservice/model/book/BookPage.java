package com.infinum.bookpublishingservice.model.book;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class BookPage {

    private int pageNumber = 0;
    private int pageSize = 10;
    private Sort.Direction sortDirection = Sort.Direction.DESC;
    private BookSortBy sortBy = BookSortBy.addedAt;
}