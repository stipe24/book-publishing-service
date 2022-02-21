package com.infinum.bookpublishingservice.model.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "author")
public class AuthorEntity {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

    @ToString.Exclude
    @ManyToMany(mappedBy = "authors")
    private Set<BookEntity> books = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        if (Objects.isNull(createdAt)) {
            createdAt = Instant.now();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        setUpdatedAt(Instant.now());
    }

}