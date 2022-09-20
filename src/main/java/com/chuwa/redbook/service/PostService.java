package com.chuwa.redbook.service;

import com.chuwa.redbook.DTO.PostDto;
import com.chuwa.redbook.DTO.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto postDto, Long id);

    void deletePostById(Long id);

}
