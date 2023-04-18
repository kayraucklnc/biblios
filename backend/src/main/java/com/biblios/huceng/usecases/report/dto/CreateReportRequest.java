package com.biblios.huceng.usecases.report.dto;

import lombok.Data;

@Data
public class CreateReportRequest {
    private final String username;
    private final String reportContent;
}
