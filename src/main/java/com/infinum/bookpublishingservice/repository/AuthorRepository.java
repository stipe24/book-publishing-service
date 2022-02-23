package com.infinum.bookpublishingservice.repository;

import com.infinum.bookpublishingservice.model.author.AuthorCount;
import com.infinum.bookpublishingservice.model.entity.AuthorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<AuthorEntity, String> {

    Optional<AuthorEntity> findByName(String name);

    @Query("select new com.infinum.bookpublishingservice.model.author.AuthorCount(a.id, a.name, count(b.id) as bookCount, a.createdAt) " +
            "from AuthorEntity a join a.books b group by a.id")
    Page<AuthorCount> countByAuthor(Pageable pageable);

}