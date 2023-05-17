package com.biblios.huceng.usecases.author.controller;


import com.biblios.huceng.bibliosentity.Author;
import com.biblios.huceng.bibliosentity.Book;
import com.biblios.huceng.entity.AppUser;
import com.biblios.huceng.startup.StartupService;
import com.biblios.huceng.usecases.author.dto.AuthorRequest;
import com.biblios.huceng.usecases.author.service.AuthorService;
import com.biblios.huceng.usecases.book.dto.BookRequest;
import com.biblios.huceng.usecases.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/author") //localhost::api/post
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;
    private final StartupService startupService;

    @PostMapping()
    public void createNewPost(@RequestBody AuthorRequest authorRequest){
//        authorService.createAuthor(authorRequest);
    }

/*    @GetMapping("/{page}")
    public Page<Author> returnAllAuthors(@PathVariable int page){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = startupService.getUser(auth.getName());

        return authorService.returnAllAuthors(page, 10);
    }*/
}
