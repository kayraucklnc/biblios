package com.biblios.huceng.usecases.comment.dto;

import lombok.Data;

@Data
public class CommentEditRequest {
    String content;
    String photoLink;
}
