package com.biblios.huceng.usecases.report.service;

import com.biblios.huceng.entity.AppUser;
import com.biblios.huceng.entity.Banned;
import com.biblios.huceng.entity.Report;
import com.biblios.huceng.entity.repository.AppUserRepository;
import com.biblios.huceng.entity.repository.BannedRepository;
import com.biblios.huceng.entity.repository.ReportRepository;
import com.biblios.huceng.exception.PastDateException;
import com.biblios.huceng.exception.PermissionDeniedException;
import com.biblios.huceng.exception.ReportDoesntExist;
import com.biblios.huceng.exception.UserDoesntExistException;
import com.biblios.huceng.util.RoleUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final AppUserRepository appUserRepository;
    private final BannedRepository bannedRepository;

    @Override
    public void createReportForUser(String username, String creator, String reportContent) {
        AppUser appUser = appUserRepository.findAppUserByUsername(username)
                .orElseThrow(() -> new UserDoesntExistException(username));

        Report report = new Report(
                null,
                reportContent,
                new Date(System.currentTimeMillis()),
                "",
                creator,
                appUser
        );

        reportRepository.save(report);
    }

    @Override
    public Collection<Report> getAllReportsForUser(String username) {
        AppUser appUser = appUserRepository.findAppUserByUsername(username)
                .orElseThrow(() -> new UserDoesntExistException(username));
        return reportRepository.findAllByAppUser(appUser);
    }

    @Override
    public Collection<Report> getAllReports() {
        return reportRepository.findAll();
    }

    @Override
    public void updateReportForUser(String adminNoteContent, Long reportId) {
        //update report stored in report table
        reportRepository.findById(reportId)
                .orElseThrow(() -> new ReportDoesntExist(reportId))
                .setAdminNotes(adminNoteContent);
    }

    @Override
    public void deleteReportForUser(Long reportId) {
        reportRepository.deleteById(reportId);
    }

    @Override
    public void clearReportsForUser(AppUser user) {
        reportRepository.deleteAll(reportRepository.findAllByAppUser(user));
    }

    @Override
    public void banUser(String username, Boolean indefinite, Long timeout) {
        AppUser appUser = appUserRepository.findAppUserByUsername(username)
                .orElseThrow(() -> new UserDoesntExistException(username));

        if(appUser.getRoles().stream().anyMatch(role -> role.getName().equals(RoleUtil.ROLE_ADMIN)))
            throw new PermissionDeniedException("Admins cannot be banned.");

        if(System.currentTimeMillis() / 1000 > timeout)
            throw new PastDateException();

        Banned banned = new Banned(null, appUser.getEmail(), appUser.getUsername(), indefinite, new Date(timeout * 1000));
        bannedRepository.save(banned);

        appUser.getAnnouncements().clear();
        appUser.getPosts().clear();
        appUser.getComments().clear();

        AppUser bannedUser = appUserRepository.findAppUserByUsername(username).orElseThrow();
        clearReportsForUser(bannedUser);
    }

    @Override
    public void unbanUser(String username) {
        Banned banned = bannedRepository.findByUsername(username)
                .orElseThrow(() -> new UserDoesntExistException(username));
        bannedRepository.delete(banned);
    }
}
