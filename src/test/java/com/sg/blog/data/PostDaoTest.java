/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blog.data;

import com.sg.blog.App;
import com.sg.blog.models.Hashtag;
import com.sg.blog.models.Post;
import com.sg.blog.models.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author mroge
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class PostDaoTest {
    @Autowired
    BlogHashtagDao hashtagDao;
    
    @Autowired
    BlogPostDao postDao;
    
    @Autowired
    BlogUserDao userDao;
    
    public PostDaoTest() {
    }
    
    @Before
    public void setUp() {
        
        List<Post> posts = postDao.getAllPosts();
        for(Post post : posts) {
            postDao.deletePostById(post.getPostId());
        }
        
        List<Hashtag> hashtags = hashtagDao.getAllHashtags();
        for(Hashtag hashtag : hashtags) {
            hashtagDao.deleteHashtagById(hashtag.getHashtagId());
        }
        
        List<User> users = userDao.getAllUsers();
        for(User user : users) {
            userDao.deleteUserById(user.getUserId());
        }
    }
    
    @Test
    public void testAddGetPost() {
        User user = new User();
        user.setFirstName("Melissa");
        user.setLastName("Rogenski");
        user.setEmail("email");
        user.setPassword("password");
        user = userDao.addUser(user);
        
        Hashtag hashtag = new Hashtag();
        hashtag.setHashtag("TestTag");
        hashtag = hashtagDao.addHashtag(hashtag);
        
        Post post = new Post();
        post.setTitle("Test Post");
        post.setPostTime(LocalDateTime.now().withNano(0));
        post.setScheduledDate(LocalDateTime.now().withNano(0));
        post.setContent("Test content for test post.");
        post.setExpirationDate(LocalDateTime.now().withNano(0));
        post.setUser(user);
        List<Hashtag> hashtags = new ArrayList<>();
        hashtags.add(hashtag);
        post.setHashtags(hashtags);
        post = postDao.addPost(post);
        
        Post fromDao = postDao.getPostById(post.getPostId());
        
        assertEquals(post, fromDao);
    }
    
    @Test
    public void testGetAllPosts() {
        User user = new User();
        user.setFirstName("Melissa");
        user.setLastName("Rogenski");
        user.setEmail("email");
        user.setPassword("password");
        user = userDao.addUser(user);
        
        Hashtag hashtag = new Hashtag();
        hashtag.setHashtag("TestTag");
        hashtag = hashtagDao.addHashtag(hashtag);
        List<Hashtag> hashtags = new ArrayList<>();
        hashtags.add(hashtag);
        
        Post post = new Post();
        post.setTitle("Test Post");
        post.setPostTime(LocalDateTime.now().withNano(0));
        post.setScheduledDate(LocalDateTime.now().withNano(0));
        post.setContent("Test content for test post.");
        post.setExpirationDate(LocalDateTime.now().withNano(0));
        post.setUser(user);
        post.setHashtags(hashtags);
        post = postDao.addPost(post);
        
        Post post2 = new Post();
        post2.setTitle("Test Post2");
        post2.setPostTime(LocalDateTime.now().withNano(0));
        post2.setScheduledDate(LocalDateTime.now().withNano(0));
        post2.setContent("Test content for test post.2");
        post2.setExpirationDate(LocalDateTime.now().withNano(0));
        post2.setUser(user);
        post2.setHashtags(hashtags);
        post2 = postDao.addPost(post2);
        
        List<Post> posts = postDao.getAllPosts();
        
        assertEquals(2, posts.size());
        assertTrue(posts.contains(post));
        assertTrue(posts.contains(post2));
    }
    
    @Test
    public void testUpdatePost() {
        User user = new User();
        user.setFirstName("Melissa");
        user.setLastName("Rogenski");
        user.setEmail("email");
        user.setPassword("password");
        user = userDao.addUser(user);
        
        Hashtag hashtag = new Hashtag();
        hashtag.setHashtag("TestTag");
        hashtag = hashtagDao.addHashtag(hashtag);
        List<Hashtag> hashtags = new ArrayList<>();
        hashtags.add(hashtag);
        
        Post post = new Post();
        post.setTitle("Test Post");
        post.setPostTime(LocalDateTime.now().withNano(0));
        post.setScheduledDate(LocalDateTime.now().withNano(0));
        post.setContent("Test content for test post.");
        post.setExpirationDate(LocalDateTime.now().withNano(0));
        post.setUser(user);
        post.setHashtags(hashtags);
        post = postDao.addPost(post);
        
        Post fromDao = postDao.getPostById(post.getPostId());
        
        assertEquals(post, fromDao);
        
        post.setTitle("Test Post2");
           
        postDao.updatePost(post);
        
        assertNotEquals(post, fromDao);
        
        fromDao = postDao.getPostById(post.getPostId());
        
        assertEquals(post, fromDao);
    }
    
    @Test
    public void testDeleteHashtag() {
        User user = new User();
        user.setFirstName("Melissa");
        user.setLastName("Rogenski");
        user.setEmail("email");
        user.setPassword("password");
        user = userDao.addUser(user);
        
        Hashtag hashtag = new Hashtag();
        hashtag.setHashtag("TestTag");
        hashtag = hashtagDao.addHashtag(hashtag);
        
        Post post = new Post();
        post.setTitle("Test Post");
        post.setPostTime(LocalDateTime.now());
        post.setScheduledDate(LocalDateTime.now());
        post.setContent("Test content for test post.");
        post.setExpirationDate(LocalDateTime.now());
        post.setUser(user);
        List<Hashtag> hashtags = new ArrayList<>();
        hashtags.add(hashtag);
        post.setHashtags(hashtags);
        post = postDao.addPost(post);
        
        postDao.deletePostById(post.getPostId());
        
        Post fromDao = postDao.getPostById(post.getPostId());
        assertNull(fromDao);
    }
}
