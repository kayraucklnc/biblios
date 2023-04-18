package com.biblios.huceng.usecases.report.dto;

import lombok.Data;

@Data
public class BanRequest {
    private final Boolean indefinite;
    private final Long duration;
}
