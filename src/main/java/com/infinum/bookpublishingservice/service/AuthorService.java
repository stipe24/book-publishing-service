package com.infinum.bookpublishingservice.service;

import com.infinum.bookpublishingservice.model.author.Author;
import com.infinum.bookpublishingservice.model.author.AuthorCount;
import com.infinum.bookpublishingservice.model.author.AuthorPage;
import com.infinum.bookpublishingservice.model.creator.PaginationCreator;
import com.infinum.bookpublishingservice.model.author.AuthorRequest;
import com.infinum.bookpublishingservice.model.entity.AuthorEntity;
import com.infinum.bookpublishingservice.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Page<AuthorCount> count(AuthorPage page){
        return authorRepository.countByAuthor(PaginationCreator.createPaginationForAuthor(page));
    }

    public Author create(AuthorRequest request) {
        if (authorExists(request.getName())) {
            throw new RuntimeException("Author with this name already exists. ");
        }
        return Author.from(authorRepository.save(new AuthorEntity(request.getName())));
    }

    public Author update(String authorId, String name) {

        var oldAuthor = authorRepository.findById(authorId);

        if (oldAuthor.isPresent()) {
                if (!oldAuthor.get().getName().equals(name) && !authorExists(name)) {
                    oldAuthor.get().setName(name);
                    return Author.from(authorRepository.save(oldAuthor.get()));
                }
        }
        throw new RuntimeException("Author with this name already exists or the name is the same.");
    }

    private boolean authorExists(String name) {
        return authorRepository.findByName(name).isPresent();
    }

}