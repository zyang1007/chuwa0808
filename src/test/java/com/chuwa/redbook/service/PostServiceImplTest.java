package com.chuwa.redbook.service;

import com.chuwa.redbook.DTO.PostDto;
import com.chuwa.redbook.DTO.PostResponse;
import com.chuwa.redbook.dao.PostRepository;
import com.chuwa.redbook.entity.Post;
import com.chuwa.redbook.exception.ResourceNotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostServiceImplTest.class);

    /**
     * Create a mock object postRepository;
     * or we can also use PostRepository postRepo =  Mockito.mock(PostRepository.class) within methods.
     * @Mock annotation will be recognized by @InjectMocks.
     */
    @Mock
    private PostRepository mockPostRepository;

    /**
     * If methods are not pre-defined, the real methods will be called.
     * To pre-defined a method's behavior, use when().thenReturn()
     */
    @Spy
    private ModelMapper modelMapper;

    /**
     * Injects the corresponding @Mock Object for this variable that this class depends on.
     */
    @InjectMocks
    private PostServiceImpl postService;

    private Post post;
    private PostDto postDto;

    @BeforeAll
    public static void beforeAll() {
        LOGGER.info("------ Starting Tests ------");
    }

    @BeforeEach
    public void setUp() {
        LOGGER.info("set up Post for each test...");
        this.post = new Post(1L, "xiao ruishi", "wanqu", "wanqu xiao rui shi",
                LocalDateTime.now(), LocalDateTime.now());

        // ModelMapper modelMapper = new ModelMapper();
        this.postDto = modelMapper.map(this.post, PostDto.class);
    }

    @AfterEach
    void printMessageAfterEachTest() {
        LOGGER.info("------ Test Ends ------\n");
    }

    @Test
    public void testCreatePost() {
        // pre-define the behaviors:
        // template: Mockito.when(mockObject.targetMethod(args)).thenReturn(desiredReturnValue);
        Mockito.when(mockPostRepository.save(ArgumentMatchers.any(Post.class))).thenReturn(post);

        // call the method to execute
        PostDto postResponse = postService.createPost(postDto);

        // check if the mockPostRepository called the save() with exactly 1 time.
        Mockito.verify(mockPostRepository, Mockito.times(1)).save(Mockito.any(Post.class));

        // assertions
        Assertions.assertNotNull(postResponse);
        Assertions.assertEquals(postDto.getTitle(), postResponse.getTitle());
        Assertions.assertEquals(postDto.getDescription(), postResponse.getDescription());
        Assertions.assertEquals(postDto.getContent(), postResponse.getContent());
    }

    @Test
    void testGetAllPosts() {
        List<Post> postList = new ArrayList<>();
        postList.add(post);

        // pre-defined the callee behavior
        Mockito.when(mockPostRepository.findAll()).thenReturn(postList);

        // call the getAllPost()
        List<PostDto> postDtoList = postService.getAllPosts();

        Assertions.assertNotNull(postDtoList);
        Assertions.assertEquals(1, postDtoList.size());
        PostDto res = postDtoList.get(0);
        Assertions.assertEquals(postDto.getTitle(), res.getTitle());
        Assertions.assertEquals(postDto.getDescription(), res.getDescription());
        Assertions.assertEquals(postDto.getContent(), res.getContent());
    }

    @Test
    void testGetAllPostsWithPaging() {
        int pageNo = 1;
        int pageSize = 8;
        String sortBy = "title";
        String sortDir = Sort.Direction.ASC.name();
        Long numOfElements = 22L;
        int numOfPages = (int) Math.ceil(numOfElements / pageSize);
        boolean isLastPage = pageNo == numOfPages;

        List<Post> posts = new ArrayList<>();
        posts.add(post);

        // define method behavior
        Page<Post> pagePosts = Mockito.mock(Page.class);

        Mockito.when(mockPostRepository.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(pagePosts);

        Mockito.when(pagePosts.getContent()).thenReturn(posts);
        Mockito.when(pagePosts.getNumber()).thenReturn(pageNo);
        Mockito.when(pagePosts.getSize()).thenReturn(pageSize);
        Mockito.when(pagePosts.getTotalElements()).thenReturn(numOfElements);
        Mockito.when(pagePosts.getTotalPages()).thenReturn(numOfPages);
        Mockito.when(pagePosts.isLast()).thenReturn(isLastPage);


        // call the method
        PostResponse postResponse = postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);

        // verify
        Assertions.assertNotNull(postResponse);
        Assertions.assertEquals(pageNo, postResponse.getPageNo());
        Assertions.assertEquals(pageSize, postResponse.getPageSize());
        Assertions.assertEquals(numOfElements, postResponse.getTotalElements());
        Assertions.assertEquals(numOfPages, postResponse.getTotalPages());
        Assertions.assertEquals(isLastPage, postResponse.isLast());

        List<PostDto> content = postResponse.getContent();
        Assertions.assertNotNull(content);
        Assertions.assertEquals(posts.size(), content.size());

        PostDto postDto1 = content.get(0);
        Assertions.assertNotNull(postDto1);
        Assertions.assertEquals(postDto.getTitle(), postDto1.getTitle());
        Assertions.assertEquals(postDto.getDescription(), postDto1.getDescription());
        Assertions.assertEquals(postDto.getContent(), postDto1.getContent());
    }

    @Test
    void testGetPostById() {
        Mockito.when(mockPostRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(post));

        PostDto postDto1 = postService.getPostById(1L);

        Assertions.assertNotNull(postDto1);
        Assertions.assertEquals(postDto.getTitle(), postDto1.getTitle());
        Assertions.assertEquals(postDto.getDescription(), postDto1.getDescription());
        Assertions.assertEquals(postDto.getContent(), postDto1.getContent());
    }

    @Test
    void testGetPostById_ResourceNotFoundException() {
        Mockito.when(mockPostRepository.findById(ArgumentMatchers.anyLong()))
                .thenThrow(new ResourceNotFoundException("Post", " id ", 1L));

        Assertions.assertThrows(ResourceNotFoundException.class, () -> postService.getPostById(1L));
    }

    @Test
    void testUpdatePost() {
        String description = "UPDATED - " + post.getDescription();
        postDto.setDescription(description);

        // deep copy
        Post updatedPost = new Post();
        updatedPost.setId(post.getId());
        updatedPost.setTitle(post.getTitle());
        updatedPost.setDescription(description);
        updatedPost.setContent(post.getContent());

        Mockito.when(mockPostRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(post));
        Mockito.when(mockPostRepository.save(ArgumentMatchers.any(Post.class))).thenReturn(updatedPost);

        PostDto postDto1 = postService.updatePost(postDto, 1L); // execute

        Assertions.assertNotNull(postDto1);
        Assertions.assertEquals(postDto.getTitle(), postDto1.getTitle());
        Assertions.assertEquals(postDto.getDescription(), postDto1.getDescription());
        Assertions.assertEquals(postDto.getContent(), postDto1.getContent());
    }

    @Test
    void testUpdatePost_ResourceNotFoundException() {
        Mockito.when(mockPostRepository.findById(ArgumentMatchers.anyLong()))
                .thenThrow(new ResourceNotFoundException("Post", " id ", 1l));

        Assertions.assertThrows(ResourceNotFoundException.class, () -> postService.updatePost(postDto, 1L));
    }

    @Test
    void testDeletePostById() {
        Mockito.when(mockPostRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(post));
        Mockito.doNothing().when(mockPostRepository).delete(ArgumentMatchers.any(Post.class));

        postService.deletePostById(1L);  // execute

        Mockito.verify(mockPostRepository, Mockito.times(1))
                .delete(ArgumentMatchers.any(Post.class));
    }

    @Test
    void testDeletePostById_ResourceNotFoundException() {
        Mockito.when(mockPostRepository.findById(Mockito.anyLong()))
                .thenThrow(new ResourceNotFoundException("Post ", "id ", 1L));

        Assertions.assertThrows(ResourceNotFoundException.class, () -> postService.deletePostById(Mockito.anyLong()));
    }
}