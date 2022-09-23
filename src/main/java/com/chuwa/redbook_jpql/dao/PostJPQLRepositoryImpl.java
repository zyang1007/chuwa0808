package com.chuwa.redbook_jpql.dao;

import com.chuwa.redbook_jpql.entity.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PostJPQLRepositoryImpl implements PostJPQLRepository {

    @PersistenceContext
    EntityManager entityManager;

    // entityManager.merge(Obj) or persist() is equivalent to postRepository.save().
    // note: postRepository extends JpaRepository.
    public Post insertPost(Post post) {
        // return entityManager.persist(post);
        return entityManager.merge(post);
    }

    // use generic
    public <T> T inserData(T t) {
        return entityManager.merge(t);
    }

    // entityManager.merge() --> update
    public Post updatePost(Post post) {
        return entityManager.merge(post);
    }

    // entityManager.remove(Obj) -->  delete
    public void deletePost(Long postId) {
        Post post = entityManager.find(Post.class, postId);
        entityManager.remove(post);
    }

    // entityManager.find(Class<T>, Obj)  --> get
    public Post getPost(Long postId) {
        return entityManager.find(Post.class, postId);
    }

    @Override
    public List<Post> getAllPostsWithJPQL() {
        TypedQuery<Post> posts = entityManager.createNamedQuery("Post.getAll", Post.class);
        return posts.getResultList();
    }

}
