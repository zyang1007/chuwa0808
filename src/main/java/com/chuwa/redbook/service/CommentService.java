package com.chuwa.redbook.service;

import com.chuwa.redbook.DTO.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId, CommentDto commentDto);

    CommentDto getCommentById(long postId, long commentId);

    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto updateComment(long postId, long commentId, CommentDto commentDto);

    void deleteComment(long postId, long commentId);

}
