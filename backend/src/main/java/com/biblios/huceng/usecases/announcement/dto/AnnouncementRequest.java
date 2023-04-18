package com.biblios.huceng.usecases.announcement.dto;

import lombok.Data;

@Data
public class AnnouncementRequest {
    private String title;
    private String content;
}