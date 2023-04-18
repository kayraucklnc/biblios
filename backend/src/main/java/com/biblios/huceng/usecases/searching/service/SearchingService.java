package com.biblios.huceng.usecases.searching.service;

import com.biblios.huceng.entity.Announcement;
import com.biblios.huceng.entity.AppUser;
import com.biblios.huceng.entity.Post;

import java.util.List;

public interface SearchingService{

    List<Post> searchPostBy(String context, int page, int size);

    List<AppUser> searchUsersBy(String searchTerm, int page, int size);

    List<Announcement> searchAnnouncementsBy(String searchTerm, int page, int size);
}
