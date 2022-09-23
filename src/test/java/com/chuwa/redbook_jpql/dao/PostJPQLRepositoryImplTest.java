package com.chuwa.redbook_jpql.dao;

import com.chuwa.redbook_jpql.entity.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.provider.HibernateUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostJPQLRepositoryImplTest {

    @Autowired
    private PostJPQLRepositoryImpl repository;

    private Post post = new Post(null, "title test", "description test",
            "content test", null, null);

    @Test
    void insertPost() {
        // title is unique
        post.setTitle(post.getTitle() + LocalDateTime.now()); // prevent duplicate title
        Post savedPost = repository.insertPost(post);
        System.out.println(savedPost);
    }

    @Test
    void inserData() {
        post.setTitle(post.getTitle() + LocalDateTime.now());
        Post savedPost = repository.insertPost(post);
        System.out.println(post);
    }

    @Test
    void updatePost() { // provide Id for update
        post.setId(12L);
        post.setTitle(post.getTitle() + LocalDateTime.now());
        Post updatedPost = repository.updatePost(post);
        System.out.println(updatedPost);
    }

    @Test
    void deletePost() {
        post.setTitle(post.getTitle() + LocalDateTime.now());
        Post savedPost = repository.insertPost(post);
        System.out.println(savedPost);

        Long id = savedPost.getId();
        repository.deletePost(id);

        Post postById = repository.getPost(id);
        System.out.println(postById);
    }

    @Test
    void getPost() {
        Post postByID = repository.getPost(12L);
        System.out.println(postByID);
    }

    @Test
    void getAllPostsWithJPQL() {
        List<Post> posts = repository.getAllPostsWithJPQL();
        System.out.println(posts);
    }


    @Test
    void testSessionFactory() {
        // create a session factory based on the config-file
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        Session session = sessionFactory.openSession(); // create a session by the factory

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();  // session starts a transaction

            // execute transaction
            post.setTitle(post.getTitle() + LocalDateTime.now());
            post.setCreateDateTime(LocalDateTime.now());
            post.setUpdateDateTime(LocalDateTime.now());

            Post savedPost = (Post) session.merge(post);
            System.out.println(savedPost);

            // commit transaction -> atomic: either success or fail
            transaction.commit();
        } catch (Exception e){
            // transaction failed
            if (transaction != null) {
                transaction.rollback();  // roll back
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}