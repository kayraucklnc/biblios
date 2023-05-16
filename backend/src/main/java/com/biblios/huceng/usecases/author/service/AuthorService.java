package com.biblios.huceng.usecases.author.service;

import com.biblios.huceng.bibliosentity.Author;


import java.util.List;

public interface AuthorService {
    Author getAuthor(String ID);

    void createAuthor(Author author);

    void createAllAuthors(List<Author> authors);

    void deleteAuthor(String ID);

    String getNationalityAuthor(String ID);

    List<Author> getAllAuthorsByNationality(String nationality);

    List<Author> getAllAuthorsByID();

    Author getAuthorByName(String name);
}
