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
public class UserDaoTest {
    @Autowired
    BlogHashtagDao hashtagDao;
    
    @Autowired
    BlogPostDao postDao;
    
    @Autowired
    BlogUserDao userDao;
    
    public UserDaoTest() {
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
    public void testAddGetUser() {
        User user = new User();
        user.setFirstName("Melissa");
        user.setLastName("Rogenski");
        user.setEmail("email@example.com");
        user.setPassword("password");
        user = userDao.addUser(user);
        
        User fromDao = userDao.getUserById(user.getUserId());
        
        assertEquals(user, fromDao);
    }
    
    @Test
    public void testGetAllUsers() {
        User user = new User();
        user.setFirstName("Melissa");
        user.setLastName("R");
        user.setEmail("email");
        user.setPassword("password");
        userDao.addUser(user);
        
        User user2 = new User();
        user2.setFirstName("Caleb");
        user2.setLastName("D");
        user2.setEmail("email");
        user2.setPassword("pass");
        userDao.addUser(user2);
        
        List<User> users = userDao.getAllUsers();
        
        assertEquals(2, users.size());
        assertTrue(users.contains(user));
        assertTrue(users.contains(user2));
    }
    
    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setFirstName("Melissa");
        user.setLastName("Rogenski");
        user.setEmail("email@example.com");
        user.setPassword("password");
        user = userDao.addUser(user);
        
        User fromDao = userDao.getUserById(user.getUserId());
        
        assertEquals(user, fromDao);
        
        user.setFirstName("Missy");
        
        userDao.updateUser(user);
        
        assertNotEquals(user, fromDao);
        
        fromDao = userDao.getUserById(user.getUserId());
        
        assertEquals(user, fromDao);
    }
    
    @Test
    public void testDeleteUser() {
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
        
        userDao.deleteUserById(user.getUserId());
        
        User fromDao = userDao.getUserById(user.getUserId());
        assertNull(fromDao);
    }
}
