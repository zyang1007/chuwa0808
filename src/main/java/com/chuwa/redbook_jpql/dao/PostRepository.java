package com.chuwa.redbook_jpql.dao;

import com.chuwa.redbook_jpql.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * JPQL
     * use entity name(Post) other than database table name
     * index parameters: first parameter(?1) -> id; second parameter(?2) -> title
     *
     * @return post entity
     */
    @Query("select p from Post p where p.id=?1 or p.title=?2")
    Post getPostByIdOrTitleWithJPQLIndexParameters(long id, String title);

    /**
     * use named parameters(format) -> :key, :title.
     * @param id corresponding to the named parameter key.
     * @param title corresponding to the named parameter title.
     * @return Post
     */
    @Query("select p from Post p where p.id=:key or p.title=:title")
    Post getPostByIdOrTitleWithJPQLNamedParameters(@Param("key") long id, @Param("title") String title);

    /**
     * SQL
     * use database table name -> posts
     * @return Post
     */
    @Query(value = "select * from posts p where p.id = ?1 or p.title = ?2", nativeQuery = true)
    Post getPostByIdOrTitleWithSQLIndexParameters(long id, String title);

    /**
     * SQL, uses database table name(posts) and named parameters(key, title).
     * @return Post
     */
    @Query(value = "select * from posts p where p.id=:key or p.title=:title", nativeQuery = true)
    Post getPostByIdOrTitleWithSQLNamedParameters(@Param("key") long id, @Param("title") String title);

}
