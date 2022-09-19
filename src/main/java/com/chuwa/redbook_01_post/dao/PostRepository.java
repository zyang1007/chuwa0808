package com.chuwa.redbook_01_post.dao;

import com.chuwa.redbook_01_post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // uses JPA Repository as well as it's provided methods,
    // so no implementation needed to be written.
}
