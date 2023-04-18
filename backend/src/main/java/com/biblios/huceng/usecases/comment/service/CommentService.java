package com.biblios.huceng.usecases.comment.service;

import com.biblios.huceng.entity.AppUser;
import com.biblios.huceng.entity.Comment;
import com.biblios.huceng.usecases.comment.dto.CommentCreateRequest;
import com.biblios.huceng.usecases.comment.dto.CommentLikeStatus;

import java.util.Collection;

public interface CommentService {
    void createComment(Comment comment);
    void createComment(CommentCreateRequest commentRequest);
    Comment getComment(Long commentID);
    Collection<Comment> getAllComments();
    Collection<Comment> getCommentsOfPost(Long postID);
    Collection<Comment> getCommentsOfUser(Long userID);

    Comment editComment(Long commentID, String content, String photoLink);

    void deleteCommentById(Long commentID);

    boolean isLikedByUser(Long commentID, AppUser user);
    CommentLikeStatus likeCommentById(Long commentID);
    void likeCommentWithUser(Long commentID, Long userID);
}
