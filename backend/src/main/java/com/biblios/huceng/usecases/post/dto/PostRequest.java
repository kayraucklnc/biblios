package com.biblios.huceng.usecases.post.dto;

import lombok.Data;

@Data
public class PostRequest {
    String title;
    String photoLink;
    String content;
}
