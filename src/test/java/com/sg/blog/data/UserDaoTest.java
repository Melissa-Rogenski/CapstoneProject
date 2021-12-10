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
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

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
        //user.setUserId(1);
        user = userDao.addUser(user);
        
        User fromDao = userDao.getUserById(user.getUserId());
        
        assertEquals(user, fromDao);
    }
    
}
