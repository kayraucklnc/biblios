package com.biblios.huceng.usecases.scholarshipJobs.service;

import com.biblios.huceng.entity.ScholarshipJob;
import com.biblios.huceng.entity.ScholarshipJobAppeal;
import com.biblios.huceng.usecases.scholarshipJobs.dto.GetScholarshipJobAppealRequest;
import com.biblios.huceng.usecases.scholarshipJobs.dto.ScholarshipJobAppealRequest;
import com.biblios.huceng.usecases.scholarshipJobs.dto.ScholarshipJobRequest;

import java.util.Collection;

public interface ScholarshipJobService {
    ScholarshipJob getScholarshipJob(Long id);
    Collection<ScholarshipJobAppeal> getAppealsOfScholarshipJob(Long scholarshipJobID);
    Collection<ScholarshipJobAppeal> getAppealsOfScholarshipJobByUser(Long scholarshipJobID, Long appealID);

    void createScholarshipJob(ScholarshipJobRequest scholarshipJobRequest);
    void createScholarshipJob(ScholarshipJob scholarshipJob);

    void deleteScholarshipJobById(Long id);

    Collection<ScholarshipJob> getAll();

    ScholarshipJob editScholarshipJobById(Long id, ScholarshipJobRequest scholarshipJobRequest);

    ScholarshipJobAppeal getAppeal(Long appealID);
    void createAppeal(ScholarshipJob scholarshipJob, ScholarshipJobAppealRequest scholarshipJobAppealRequest);
    void createAppeal(ScholarshipJobAppeal scholarshipJobAppeal);
    ScholarshipJobAppeal editAppeal(Long appealID, ScholarshipJobAppealRequest scholarshipJobAppealRequest);
    void deleteAppeal(Long appealID);

    ScholarshipJob convertToEntity(ScholarshipJobRequest scholarshipJobRequest);
    ScholarshipJobAppeal convertToEntity(ScholarshipJobAppealRequest scholarshipJobAppealRequest);
    ScholarshipJobRequest convertToDto(ScholarshipJob scholarshipJob);
    GetScholarshipJobAppealRequest convertToDto(ScholarshipJobAppeal scholarshipJobAppeal);
}
