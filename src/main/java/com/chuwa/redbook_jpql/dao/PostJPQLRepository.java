package com.chuwa.redbook_jpql.dao;

import com.chuwa.redbook_jpql.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostJPQLRepository {
    List<Post> getAllPostsWithJPQL();
}
