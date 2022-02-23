package com.infinum.bookpublishingservice.model.creator;

import com.infinum.bookpublishingservice.model.author.AuthorPage;
import com.infinum.bookpublishingservice.model.book.BookPage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PaginationCreator {

    public static Pageable createPaginationForAuthor(AuthorPage page) {
        Sort sort = Sort.by(page.getSortDirection(), page.getSortBy().name());
        return PageRequest.of(page.getPageNumber(), page.getPageSize(), sort);
    }

    public static Pageable createPaginationForReport() {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        return PageRequest.of(0, 1, sort);
    }

    public static Pageable createPaginationForBook(BookPage page) {
        Sort sort = Sort.by(page.getSortDirection(), page.getSortBy().name());
        return PageRequest.of(page.getPageNumber(), page.getPageSize(), sort);
    }

}