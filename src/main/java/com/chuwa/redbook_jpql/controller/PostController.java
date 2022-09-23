package com.chuwa.redbook_jpql.controller;

import com.chuwa.redbook_jpql.entity.Post;
import com.chuwa.redbook_jpql.payload.PostDto;
import com.chuwa.redbook_jpql.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // JPQL mapping methods:
    @GetMapping("/jpql")
    public List<PostDto> getAllPostsWithJPQL() {
        return postService.getAllPostsWithJPQL();
    }

    @GetMapping("/jpql-index/{postId}")
    public ResponseEntity<PostDto> getPostByIdOrTitleJPQLIndex(@PathVariable(name = "postId") long id,
                                                               @RequestParam(value = "title", required = false) String title) {
        return ResponseEntity.ok(postService.getPostByIdJPQLIndexParam(id, title));
    }

    @GetMapping("/jpql-named/{postId}")
    public ResponseEntity<PostDto> getPostByIdOrTitleJPQLNamedParam(@PathVariable(name = "postId") long id,
                                                                    @RequestParam(value = "title", required = false) String title) {

        return ResponseEntity.ok(postService.getPostByIdJPQLNamedParam(id, title));
    }

    @GetMapping("/sql-index/{postId}")
    public ResponseEntity<PostDto> getPostByIdOrTitleSQLIndex(@PathVariable(name = "postId") long id,
                                                              @RequestParam(value = "title", required = false) String title) {
        return ResponseEntity.ok(postService.getPostByIdSQLIndexParam(id, title));
    }

    @GetMapping("/sql-named/{postId}")
    public ResponseEntity<PostDto> getPostByIdOrTitleSQLNamedParam(@PathVariable(name = "postId") long id,
                                                                   @RequestParam(value = "title", required = false) String title) {
        return ResponseEntity.ok(postService.getPostByIdSQLNamedParam(id, title));
    }


    // JPA repository/mapping methods:

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "postId") long postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @GetMapping
    public List<PostDto> getAllPosts() {
        return postService.getAllPost();
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,
                                              @PathVariable(name = "postId") long id) {
        return new ResponseEntity<>(postService.updatePost(postDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "postId") long postId) {
        postService.deletePostById(postId);
        return new ResponseEntity<>("Post deleted successfully!", HttpStatus.OK);
    }
}
