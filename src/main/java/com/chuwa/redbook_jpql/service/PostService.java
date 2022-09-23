package com.chuwa.redbook_jpql.service;

import com.chuwa.redbook_jpql.entity.Post;
import com.chuwa.redbook_jpql.payload.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostDto getPostById(Long postId);

    List<PostDto> getAllPost();

    PostDto updatePost(PostDto postDto, Long postId);

    void deletePostById(Long postId);

    List<PostDto> getAllPostsWithJPQL();

    PostDto getPostByIdJPQLIndexParam(Long postId, String title);
    PostDto getPostByIdJPQLNamedParam(Long postId, String title);
    PostDto getPostByIdSQLIndexParam(Long postId, String title);
    PostDto getPostByIdSQLNamedParam(Long postId, String title);

}
