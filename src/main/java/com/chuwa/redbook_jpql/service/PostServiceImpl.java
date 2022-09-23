package com.chuwa.redbook_jpql.service;

import com.chuwa.redbook_jpql.dao.PostJPQLRepository;
import com.chuwa.redbook_jpql.dao.PostRepository;
import com.chuwa.redbook_jpql.entity.Post;
import com.chuwa.redbook_jpql.exception.ResourceNotFoundException;
import com.chuwa.redbook_jpql.payload.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostJPQLRepository postJPQLRepository;

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post savedPost = postRepository.save(post);

        return mapToDto(savedPost);
    }

    @Override
    public PostDto getPostById(Long postId) {
        Post post = searchPostById(postId);

        return mapToDto(post);
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> posts = postRepository.findAll();

        return posts.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        Post post = searchPostById(postId);
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);
        return mapToDto(updatedPost);
    }

    @Override
    public void deletePostById(Long postId) {
        Post post = searchPostById(postId);
        postRepository.delete(post);
    }

    @Override
    public List<PostDto> getAllPostsWithJPQL() {
        return postJPQLRepository.getAllPostsWithJPQL().stream().map(e -> mapToDto(e)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostByIdJPQLIndexParam(Long postId, String title) {
        Post post = postRepository.getPostByIdOrTitleWithJPQLIndexParameters(postId, title);
        return mapToDto(post);
    }

    @Override
    public PostDto getPostByIdJPQLNamedParam(Long postId, String title) {
        Post post = postRepository.getPostByIdOrTitleWithJPQLNamedParameters(postId, title);
        return mapToDto(post);
    }

    @Override
    public PostDto getPostByIdSQLIndexParam(Long postId, String title) {
        Post post = postRepository.getPostByIdOrTitleWithSQLIndexParameters(postId, title);
        return mapToDto(post);
    }

    @Override
    public PostDto getPostByIdSQLNamedParam(Long postId, String title) {
        Post post = postRepository.getPostByIdOrTitleWithSQLNamedParameters(postId, title);
        return mapToDto(post);
    }

    // helper methods:
    private PostDto mapToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());

        return postDto;
    }

    private Post mapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        return post;
    }

    private Post searchPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
    }
}
