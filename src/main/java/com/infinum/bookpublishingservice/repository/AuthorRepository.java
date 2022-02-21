package com.infinum.bookpublishingservice.repository;

import com.infinum.bookpublishingservice.model.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorEntity, String> {

}