package com.biblios.huceng.startup;

import com.biblios.huceng.entity.AppUser;
import com.biblios.huceng.entity.Role;

public interface StartupService {

    AppUser saveUser(AppUser user);

    Role saveRole(Role role);

    void assignRoleToUser(String username, String roleName);

    AppUser getUser(String username);

    AppUser getUser(Long id);
}
