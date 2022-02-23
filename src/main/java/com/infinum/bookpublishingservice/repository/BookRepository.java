package com.infinum.bookpublishingservice.repository;

import com.infinum.bookpublishingservice.model.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookRepository extends JpaRepository<BookEntity, String> , JpaSpecificationExecutor<BookEntity> {

}