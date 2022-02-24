package com.infinum.bookpublishingservice.model.author;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorCount {

    @JsonIgnore
    private String id;
    private String name;
    private Long bookCount;
    private Instant createdAt;
}