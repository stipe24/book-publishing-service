package com.infinum.bookpublishingservice.service;

import com.infinum.bookpublishingservice.model.book.BookFilter;
import com.infinum.bookpublishingservice.model.creator.BookMatcher;
import com.infinum.bookpublishingservice.model.book.BookPage;
import com.infinum.bookpublishingservice.model.creator.PaginationCreator;
import com.infinum.bookpublishingservice.model.book.BookRequest;
import com.infinum.bookpublishingservice.model.entity.AuthorEntity;
import com.infinum.bookpublishingservice.model.entity.BookEntity;
import com.infinum.bookpublishingservice.model.entity.GenreEntity;
import com.infinum.bookpublishingservice.repository.AuthorRepository;
import com.infinum.bookpublishingservice.repository.BookRepository;
import com.infinum.bookpublishingservice.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    public Page<BookEntity> findByFilter(BookFilter filter, BookPage page) {
        return bookRepository.findAll(BookMatcher.match(filter), PaginationCreator.createPaginationForBook(page));
    }

    public List<BookEntity> findByFilter(BookFilter filter) {
        return bookRepository.findAll(BookMatcher.match(filter));
    }

    public BookEntity create(BookRequest request) {

        return bookRepository.save(createFromRequest(request));
    }

    private BookEntity createFromRequest(BookRequest request) {

        BookEntity book = new BookEntity();
        book.setTitle(request.getTitle());
        book.setPublishedAt(request.getPublishedAt());
        book.setIsbn(request.getIsbn());

        request.getAuthorIds().forEach(authorId -> {
            var oldAuthor = authorRepository.findById(authorId);
            if (oldAuthor.isPresent()) {
                AuthorEntity author = new AuthorEntity();
                author.setId(oldAuthor.get().getId());
                author.setName(oldAuthor.get().getName());
                author.setCreatedAt(oldAuthor.get().getCreatedAt());
                author.setUpdatedAt(oldAuthor.get().getUpdatedAt());
                book.addAuthor(author);
            }
            else {
                throw new RuntimeException("Invalid request. Author id: " + authorId + " not valid.");
            }
        });

        request.getGenreIds().forEach(genreId -> {
            var oldGenre = genreRepository.findById(genreId);
            if (oldGenre.isPresent()) {
                GenreEntity genre = new GenreEntity();
                genre.setId(oldGenre.get().getId());
                genre.setName(oldGenre.get().getName());
                book.addGenre(genre);
            }
            else {
                throw new RuntimeException("Invalid request. Genre id: " + genreId + " not valid.");
            }
        });

        return book;
    }

}