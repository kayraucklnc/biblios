package com.biblios.huceng.usecases.shelf.controller;


import com.biblios.huceng.startup.StartupService;
import com.biblios.huceng.usecases.series.service.SeriesService;
import com.biblios.huceng.usecases.shelf.service.ShelfServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/shelf") //localhost::api/post
@RequiredArgsConstructor
public class ShelfController {


    private final ShelfServiceImpl shelfService;
    private final StartupService startupService;


}
