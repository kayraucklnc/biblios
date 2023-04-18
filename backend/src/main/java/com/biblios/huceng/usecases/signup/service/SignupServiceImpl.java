package com.biblios.huceng.usecases.signup.service;

import com.biblios.huceng.entity.AppUser;
import com.biblios.huceng.entity.PendingUser;
import com.biblios.huceng.entity.Profile;
import com.biblios.huceng.entity.Role;
import com.biblios.huceng.entity.repository.*;
import com.biblios.huceng.exception.UsernameAlreadyExists;
import com.biblios.huceng.usecases.signup.dto.PendingUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SignupServiceImpl implements SignupService {
    private final PendingUserRepository pendingUserRepository;
    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    private final ProfileRepository profileRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public PendingUser saveUserAsPending(PendingUser pendingUser) {
        log.info("Pending user {}", pendingUser);
        List<String> usernames = appUserRepository.getAllUsernames();
        List<String> pendingUsernames = pendingUserRepository.getAllUsernames();
        List<String> toCheck = Stream.concat(usernames.stream(), pendingUsernames.stream()).toList();
        log.info("Usernames {}", toCheck);

        if (toCheck.contains(pendingUser.getUsername()))
            throw new UsernameAlreadyExists(pendingUser.getUsername());

        return pendingUserRepository.save(pendingUser);
    }

    /*
    Called by the Admin. Moves the user from Pending table to AppUserTable.
    delete -> save.
     */
    @Override
    public void approveUser(String username) {
        PendingUser pendingUser = pendingUserRepository.findPendingUserByUsername(username).orElseThrow();
        AppUser appUser = new AppUser(
                null,
                pendingUser.getFirstName(),
                pendingUser.getLastName(),
                pendingUser.getEmail(),
                pendingUser.getUsername(),
                encoder.encode(pendingUser.getPassword()),
                pendingUser.getRoles()
        );
        Profile profile = new Profile();
        profile.setAppUser(appUser);
        profileRepository.save(profile);
        appUser.setProfile(profile);

        appUserRepository.save(appUser);
        pendingUserRepository.delete(pendingUser);
    }

    @Override
    public void denyUser(String username) {
        PendingUser pendingUser = pendingUserRepository.findPendingUserByUsername(username).orElseThrow();
        pendingUserRepository.delete(pendingUser);
    }

    @Override
    public void assignRoleToPendingUser(String username, String roleName) {
        PendingUser user = pendingUserRepository.findPendingUserByUsername(username).orElseThrow();
        Role role = roleRepository.findByName(roleName).orElseThrow();

        user.getRoles().add(role);
    }

    @Override
    public List<PendingUserResponse> getAllPendingUsers() {
        return pendingUserRepository.findAll()
                .stream()
                .map((user) -> new PendingUserResponse(user.getUsername(), user.getEmail(), user.getRoles().stream().toList().get(0).getName()))
                .collect(Collectors.toList());
    }
}
