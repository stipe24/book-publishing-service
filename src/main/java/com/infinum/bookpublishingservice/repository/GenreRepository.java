package com.infinum.bookpublishingservice.repository;

import com.infinum.bookpublishingservice.model.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<GenreEntity, String> {

}