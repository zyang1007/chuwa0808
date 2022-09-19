package com.chuwa.redbook_01_post.controller;

import com.chuwa.redbook_01_post.DTO.PostDto;
import com.chuwa.redbook_01_post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        // call the service layer to execute the creation request/operation.
        PostDto postResponse = postService.createPost(postDto);

        return new ResponseEntity<>(postResponse, HttpStatus.CREATED);
    }
}
