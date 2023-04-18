package com.biblios.huceng.usecases.scholarshipJobs.dto;

import com.biblios.huceng.entity.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetScholarshipJobAppealRequest {
    private Long id;
    private String description;
    private String resumeFileName;
    private AppUser applicant;
}
