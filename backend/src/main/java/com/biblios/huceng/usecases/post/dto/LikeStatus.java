package com.biblios.huceng.usecases.post.dto;

import lombok.Data;

@Data
public class LikeStatus {
    boolean isLiked;

    public LikeStatus(boolean isLiked) {
        this.isLiked = isLiked;
    }
}
