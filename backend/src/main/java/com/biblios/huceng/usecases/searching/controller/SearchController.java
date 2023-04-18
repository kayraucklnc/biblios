package com.biblios.huceng.usecases.searching.controller;

import com.biblios.huceng.entity.Announcement;
import com.biblios.huceng.entity.AppUser;
import com.biblios.huceng.entity.Post;
import com.biblios.huceng.usecases.searching.dto.RequestParams;
import com.biblios.huceng.usecases.searching.dto.SearchResponseDTO;
import com.biblios.huceng.usecases.searching.service.SearchingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/search/")
@RequiredArgsConstructor
@Slf4j
public class SearchController {

    final private SearchingService searchingService;

    @PostMapping()
    public SearchResponseDTO postContainsText(@RequestBody RequestParams requestParam) {
        List<Post> posts = searchingService.searchPostBy(requestParam.getSearchTerm(), requestParam.getPage(), requestParam.getSize());
        List<AppUser> users = searchingService.searchUsersBy(requestParam.getSearchTerm(), requestParam.getPage(), requestParam.getSize());
        List<Announcement> announcements = searchingService.searchAnnouncementsBy(requestParam.getSearchTerm(), requestParam.getPage(), requestParam.getSize());
        return new SearchResponseDTO(posts, users, announcements);
    }
}
