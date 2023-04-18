package com.biblios.huceng.entity.repository;

import com.biblios.huceng.entity.AppUser;
import com.biblios.huceng.entity.ScholarshipJob;
import com.biblios.huceng.entity.ScholarshipJobAppeal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScholarshipJobAppealRepository extends JpaRepository<ScholarshipJobAppeal, Long> {
    List<ScholarshipJobAppeal> findAllByOrderByTimestamp();

    List<ScholarshipJobAppeal> findAllByParentScholarshipJobAndApplicant(ScholarshipJob scholarshipJob, AppUser user);
}
