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
public class HashtagDaoTest {
    @Autowired
    BlogHashtagDao hashtagDao;
    
    @Autowired
    BlogPostDao postDao;
    
    @Autowired
    BlogUserDao userDao;
    
    public HashtagDaoTest() {
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
    public void testAddGetHashtag() {
        Hashtag hashtag = new Hashtag();
        hashtag.setHashtag("TestTag");
        hashtag = hashtagDao.addHashtag(hashtag);
        
        Hashtag fromDao = hashtagDao.getHashtagById(hashtag.getHashtagId());
        
        assertEquals(hashtag, fromDao);
    }
    
    @Test
    public void testGetAllPosts() {
        Hashtag hashtag = new Hashtag();
        hashtag.setHashtag("TestTag");
        hashtag = hashtagDao.addHashtag(hashtag);
        
        Hashtag hashtag2 = new Hashtag();
        hashtag2.setHashtag("TestTag2");
        hashtag2 = hashtagDao.addHashtag(hashtag2);
        
        List<Hashtag> hashtags = hashtagDao.getAllHashtags();
        
        assertEquals(2, hashtags.size());
        assertTrue(hashtags.contains(hashtag));
        assertTrue(hashtags.contains(hashtag2));
    }
    
    @Test
    public void testUpdatePost() {
        Hashtag hashtag = new Hashtag();
        hashtag.setHashtag("TestTag");
        hashtag = hashtagDao.addHashtag(hashtag);
        
        Hashtag fromDao = hashtagDao.getHashtagById(hashtag.getHashtagId());
        
        assertEquals(hashtag, fromDao);
        
        hashtag.setHashtag("Another TestTag");
        
        hashtagDao.updateHashtag(hashtag);
        
        assertNotEquals(hashtag, fromDao);
        
        fromDao = hashtagDao.getHashtagById(hashtag.getHashtagId());
        
        assertEquals(hashtag, fromDao);
    }
    
    @Test
    public void testDeletePost() {
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
        
        hashtagDao.deleteHashtagById(hashtag.getHashtagId());
        
        Hashtag fromDao = hashtagDao.getHashtagById(hashtag.getHashtagId());
        assertNull(fromDao);
    }
}
