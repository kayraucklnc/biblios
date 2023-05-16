package com.biblios.huceng.usecases.author.service;

import com.biblios.huceng.bibliosentity.Author;
import com.biblios.huceng.bibliosentity.Book;
import com.biblios.huceng.bibliosentity.bibliosrepository.AuthorRepository;
import com.biblios.huceng.bibliosentity.bibliosrepository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthorServiceImpl implements AuthorService{


    private final AuthorRepository authorRepository;


    @Override
    public Author getAuthor(String ID) {
        return authorRepository.getAuthorByID(ID);
    }

    @Override
    public void createAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void createAllAuthors(List<Author> authors) {
        authorRepository.saveAll(authors);
    }

    @Override
    public void deleteAuthor(String ID) {
        authorRepository.deleteById(ID);
    }

    @Override
    public String getNationalityAuthor(String ID) {
        return authorRepository.getNationalityAuthor(ID);
    }

    @Override
    public List<Author> getAllAuthorsByNationality(String nationality) {
        return authorRepository.getAllAuthorsByNationality(nationality);
    }

    @Override
    public List<Author> getAllAuthorsByID() {
        return authorRepository.getAllAuthorsByID();
    }

    @Override
    public Author getAuthorByName(String name) {
        return authorRepository.getAuthorByName(name);
    }




}
