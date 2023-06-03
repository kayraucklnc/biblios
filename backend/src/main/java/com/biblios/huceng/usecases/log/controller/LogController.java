package com.biblios.huceng.usecases.log.controller;

import com.biblios.huceng.bibliosentity.Book;
import com.biblios.huceng.bibliosentity.Log;
import com.biblios.huceng.entity.AppUser;
import com.biblios.huceng.startup.StartupService;
import com.biblios.huceng.usecases.log.service.LogService;
import com.biblios.huceng.usecases.log.service.LogServiceImpl;
import com.biblios.huceng.usecases.user.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(path = "/api/log") //localhost::api/user
@RequiredArgsConstructor
public class LogController {
    private final LogService logService;

    @GetMapping()
    public List<Log> getLogs() {
        return logService.getLogs();
    }
}
