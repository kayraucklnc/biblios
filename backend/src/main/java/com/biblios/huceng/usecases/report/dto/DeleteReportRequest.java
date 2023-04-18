package com.biblios.huceng.usecases.report.dto;

import lombok.Data;

@Data
public class DeleteReportRequest {
    private final String username;
    private final Long reportId;
}
