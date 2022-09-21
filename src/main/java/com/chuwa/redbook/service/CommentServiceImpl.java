package com.chuwa.redbook.service;

import com.chuwa.redbook.DTO.CommentDto;
import com.chuwa.redbook.dao.CommentRepository;
import com.chuwa.redbook.dao.PostRepository;
import com.chuwa.redbook.entity.Comment;
import com.chuwa.redbook.entity.Post;
import com.chuwa.redbook.exception.BlogAPIException;
import com.chuwa.redbook.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);  // convert the commentDto to comment entity

        Post post = searchPostById(postId); // retrieve post entity by postId

        comment.setPost(post); // set post to the comment entity

        Comment savedComment = commentRepository.save(comment); // save the comment entity into the database

        return mapToDto(savedComment);
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        Post post = searchPostById(postId);
        Comment comment = searchCommentById(commentId);

        // business logic: ensure the post IDs are identical
        if (!post.getId().equals(comment.getPost().getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post!");
        }
        return mapToDto(comment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        // retrieve comments by post id
        List<Comment> comments = commentRepository.findByPostId(postId);

        // covert comment entity to dto and return them.
        return comments.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentDto) {
        Post post = searchPostById(postId);
        Comment comment = searchCommentById(commentId);
        if (!post.getId().equals(comment.getPost().getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post!");
        }

        // modify the comment entity
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        // save the modified comment entity to database
        Comment updatedComment = commentRepository.save(comment);

        return mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(long postId, long commentId) {
        Post post = searchPostById(postId);
        Comment comment = searchCommentById(commentId);
        if (!post.getId().equals(comment.getPost().getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to the post!");
        }

        commentRepository.delete(comment);
    }

    // helper methods:
    private CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());

        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        return comment;
    }

    // this method retrieve post entity from database by a post id.
    private Post searchPostById(long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
    }

    // this method retrieve comment entity from database by a comment id.
    private Comment searchCommentById(long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
    }
}
