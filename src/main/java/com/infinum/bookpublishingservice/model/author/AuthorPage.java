package com.infinum.bookpublishingservice.model.author;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class AuthorPage {

    private int pageNumber = 0;
    private int pageSize = 10;
    private Sort.Direction sortDirection = Sort.Direction.DESC;
    private AuthorSortBy sortBy = AuthorSortBy.createdAt;

}