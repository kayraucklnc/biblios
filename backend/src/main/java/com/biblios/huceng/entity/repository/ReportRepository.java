package com.biblios.huceng.entity.repository;

import com.biblios.huceng.entity.AppUser;
import com.biblios.huceng.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findAllByAppUser(AppUser appUser);

}
