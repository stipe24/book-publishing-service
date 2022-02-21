package com.infinum.bookpublishingservice.model.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private Instant publishedAt;

    @Column(updatable = false)
    private Instant addedAt;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<AuthorEntity> authors;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(
            name = "book_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<GenreEntity> genres;

    @PrePersist
    protected void onCreate() {
        if (Objects.isNull(addedAt)) {
            addedAt = Instant.now();
        }
    }

}