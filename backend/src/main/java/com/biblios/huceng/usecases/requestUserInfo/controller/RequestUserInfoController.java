package com.biblios.huceng.usecases.requestUserInfo.controller;

import com.biblios.huceng.entity.AppUser;
import com.biblios.huceng.usecases.requestUserInfo.dto.PagingRequest;
import com.biblios.huceng.usecases.requestUserInfo.dto.ProfileSummary;
import com.biblios.huceng.usecases.requestUserInfo.service.RequestUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/requestinfo")
@RequiredArgsConstructor
@Slf4j
public class RequestUserInfoController {
    private final RequestUserService requestUserService;

    @GetMapping("/{username}")
    public ProfileSummary getProfileSummary(@PathVariable String username) {
        return requestUserService.getProfileSummary(username);
    }

    @PostMapping("/all")
    public Page<AppUser> getAllUsers(@RequestBody PagingRequest pagingRequest) {
        return requestUserService.getAllUsers(pagingRequest.getPage(), pagingRequest.getSize());
    }

}
