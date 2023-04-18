package com.biblios.huceng.usecases.comment.dto;

import lombok.Data;

@Data
public class CommentCreateRequest {
    Long postID;
    String content;
    String photoLink;
}
