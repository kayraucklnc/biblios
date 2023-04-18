package com.biblios.huceng.usecases.requestUserInfo.dto;

import lombok.Data;

@Data
public class PagingRequest {
    private int page;
    private int size;
}
