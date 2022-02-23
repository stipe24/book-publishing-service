package com.infinum.bookpublishingservice.model.author;


import com.infinum.bookpublishingservice.model.entity.AuthorEntity;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class Author {

    private String name;

    private Instant createdAt;

    private Instant updatedAt;

    public static Author from(AuthorEntity authorEntity) {
        return Author.builder()
                .name(authorEntity.getName())
                .createdAt(authorEntity.getCreatedAt())
                .updatedAt(authorEntity.getUpdatedAt())
                .build();
    }
}