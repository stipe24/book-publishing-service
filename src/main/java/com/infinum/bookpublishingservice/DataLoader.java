package com.infinum.bookpublishingservice;

import com.infinum.bookpublishingservice.model.entity.AuthorEntity;
import com.infinum.bookpublishingservice.model.entity.BookEntity;
import com.infinum.bookpublishingservice.model.entity.GenreEntity;
import com.infinum.bookpublishingservice.repository.AuthorRepository;
import com.infinum.bookpublishingservice.repository.BookRepository;
import com.infinum.bookpublishingservice.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@AllArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        populateData();
    }

    private void populateData() {
        GenreEntity fantasy = new GenreEntity("Fantasy");
        GenreEntity mystery = new GenreEntity("Mystery");
        GenreEntity horror = new GenreEntity("Horror");
        GenreEntity thriller = new GenreEntity("Thriller");
        GenreEntity romance = new GenreEntity("Romance");

        genreRepository.saveAll(List.of(fantasy, mystery, horror, thriller, romance));

        AuthorEntity shakespeare = new AuthorEntity("William Shakespeare");
        AuthorEntity rowling = new AuthorEntity("J. K. Rowling");
        AuthorEntity christie = new AuthorEntity("Agatha Christie");
        AuthorEntity dostojevski = new AuthorEntity("Fjodor Mihajlovič Dostojevski");

        authorRepository.saveAll(List.of(shakespeare, rowling, christie, dostojevski));

        BookEntity hamlet = new BookEntity();
        hamlet.setTitle("Hamlet");
        hamlet.setIsbn("123456789");
        hamlet.setPublishedAt(Instant.now());
        hamlet.addAuthor(dostojevski);
        hamlet.addGenre(romance);

        bookRepository.save(hamlet);

        BookEntity java = new BookEntity();
        java.setTitle("Test book");
        java.setIsbn("987654321");
        java.setPublishedAt(Instant.now());
        java.addAuthor(dostojevski);
        java.addAuthor(christie);
        java.addGenre(thriller);

        bookRepository.save(java);
    }

}