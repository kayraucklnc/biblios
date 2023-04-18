package com.biblios.huceng.usecases.searching.dto;

import com.biblios.huceng.entity.Announcement;
import com.biblios.huceng.entity.AppUser;
import com.biblios.huceng.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SearchResponseDTO {
    private List<Post> posts;
    private List<AppUser> appUsers;
    private List<Announcement> announcements;

}
