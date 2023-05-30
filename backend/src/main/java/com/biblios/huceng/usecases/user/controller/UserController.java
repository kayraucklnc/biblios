package com.biblios.huceng.usecases.user.controller;

import com.biblios.huceng.bibliosentity.Book;
import com.biblios.huceng.entity.AppUser;
import com.biblios.huceng.startup.StartupService;
import com.biblios.huceng.usecases.user.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path = "/api/user") //localhost::api/user
@RequiredArgsConstructor
public class UserController {
    private final StartupService startupService;
    private final AppUserService appUserService;

    @GetMapping("{userID}")
    public AppUser getUserByID(@PathVariable Long userID) {
        return startupService.getUser(userID);
    }

    @GetMapping("username/{username}")
    public AppUser getUserByUsername(@PathVariable String username) {
        return startupService.getUser(username);
    }
    @GetMapping("books/{profilename}/{page}")
    public Page<Book> getUsersBorrowedBooks(@PathVariable String profilename, @PathVariable int page) {
        Long id = appUserService.getIdFromProfileName(profilename);
        return appUserService.getBorrowedBooksForAppUserWithID(id, page);
    }
}
