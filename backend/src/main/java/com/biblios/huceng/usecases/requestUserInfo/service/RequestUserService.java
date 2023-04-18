package com.biblios.huceng.usecases.requestUserInfo.service;

import com.biblios.huceng.entity.AppUser;
import com.biblios.huceng.usecases.requestUserInfo.dto.ProfileSummary;
import org.springframework.data.domain.Page;

public interface RequestUserService {

    ProfileSummary getProfileSummary(String username);

    Page<AppUser> getAllUsers(int page, int size);
}
