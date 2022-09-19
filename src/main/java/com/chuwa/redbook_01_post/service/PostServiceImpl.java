package com.chuwa.redbook_01_post.service;

import com.chuwa.redbook_01_post.DTO.PostDto;
import com.chuwa.redbook_01_post.dao.PostRepository;
import com.chuwa.redbook_01_post.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;


    @Override
    public PostDto createPost(PostDto postDto) {
        // transfers the request body to a Post entity
        Post post = new Post();
        if (postDto.getTitle() != null) {
            post.setTitle(postDto.getTitle());
        } else {
            post.setTitle("");
        }
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        // save the new Post to the database using JPA's DAO
        Post savedPost = postRepository.save(post);

        // send the returned data to controller,
        // note that controller takes only DTO.
        PostDto postResponse = new PostDto();
        postResponse.setId(savedPost.getId());
        postResponse.setTitle(savedPost.getTitle());
        postResponse.setDescription(savedPost.getDescription());
        postResponse.setContent(savedPost.getContent());

        return postResponse;
    }
}
