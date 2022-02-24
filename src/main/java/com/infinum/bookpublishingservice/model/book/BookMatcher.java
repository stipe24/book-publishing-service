package com.infinum.bookpublishingservice.model.book;

import com.infinum.bookpublishingservice.model.entity.AuthorEntity;
import com.infinum.bookpublishingservice.model.entity.BookEntity;
import com.infinum.bookpublishingservice.model.entity.GenreEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class BookMatcher {

    private final List<Predicate> predicates = new ArrayList<>();

    private final Root<BookEntity> root;
    private final BookFilter filter;
    private final CriteriaBuilder builder;

    public BookMatcher(Root<BookEntity> root, BookFilter filter, CriteriaBuilder builder) {
        this.root = root;
        this.filter = filter;
        this.builder = builder;
    }

    public static Specification<BookEntity> match(BookFilter filter) {

        return (root, query, builder) -> {

            var matcher = new BookMatcher(root, filter, builder);

            matcher.matchTitle();
            matcher.matchIsbn();
            matcher.matchGenre();
            matcher.matchAuthor();
            matcher.matchAdded();

            return builder.and(matcher.getPredicates());
        };
    }

    private void matchTitle() {
        if (filter.getTitle() != null) {
            Predicate title = builder.like(root.get("title"), filter.getTitle());
            predicates.add(title);
        }
    }

    private void matchIsbn() {
        if (filter.getIsbn() != null) {
            Predicate isbn = builder.equal(root.get("isbn"), filter.getIsbn());
            predicates.add(isbn);
        }
    }

    private void matchGenre() {
        if (filter.getGenreIds() != null) {
            Join<BookEntity, GenreEntity> join = root.join("genres", JoinType.LEFT);
            Predicate genreId = join.get("id").in(filter.getGenreIds().stream().map(String::valueOf).collect(Collectors.toList()));
            predicates.add(genreId);
        }
    }

    private void matchAuthor() {
        if (filter.getAuthorIds() != null) {
            Join<BookEntity, AuthorEntity> join = root.join("authors", JoinType.LEFT);
            Predicate authorId = join.get("id").in(filter.getAuthorIds().stream().map(String::valueOf).collect(Collectors.toList()));
            predicates.add(authorId);
        }
    }

    private void matchAdded() {
        if (filter.getAddedAt() != null) {
            Predicate added = builder.greaterThanOrEqualTo(root.get("addedAt"), filter.getAddedAt().toInstant());
            predicates.add(added);
        }
    }

    private Predicate[] getPredicates() {
        return predicates.toArray(new Predicate[0]);
    }
}