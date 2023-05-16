package com.biblios.huceng.usecases.series.controller;


import com.biblios.huceng.startup.StartupService;
import com.biblios.huceng.usecases.book.dto.BookRequest;
import com.biblios.huceng.usecases.publisher.service.PublisherService;
import com.biblios.huceng.usecases.series.dto.SeriesRequest;
import com.biblios.huceng.usecases.series.service.SeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/series") //localhost::api/post
@RequiredArgsConstructor
public class SeriesController {

    private final SeriesService seriesService;
    private final StartupService startupService;

    @PostMapping()
    public void createNewPost(@RequestBody SeriesRequest seriesRequest){
//        bookService.createBook(bookRequest);
    }

/*    @GetMapping("/{page}")
    public Page<Book> returnAllBooks(@PathVariable int page){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = startupService.getUser(auth.getName());

        return bookService.returnAllBooks(page, 10);
    }*/
}
