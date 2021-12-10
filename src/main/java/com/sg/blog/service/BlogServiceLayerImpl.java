/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.blog.service;

import com.sg.blog.data.BlogHashtagDao;
import com.sg.blog.data.BlogPostDao;
import com.sg.blog.data.BlogUserDao;
import com.sg.blog.models.Hashtag;
import com.sg.blog.models.Post;
import com.sg.blog.models.User;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author calebdiaz
 */
@Repository
public class BlogServiceLayerImpl implements BlogServiceLayer {
    
    private BlogPostDao postDao;
    private BlogUserDao userDao;
    private BlogHashtagDao hashtagDao;

    @Autowired
    public BlogServiceLayerImpl(BlogPostDao postDao, BlogUserDao userDao, BlogHashtagDao hashtagDao) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.hashtagDao = hashtagDao;
    }
    
    @Override
    public List<Post> home() {
        return postDao.getAllPosts();
    }

    @Override
    public List<Post> getPosts(PostQueryContext query) {
        return postDao.getAllPosts(query);
    }

    @Override
    public Post getPostById(int id) {
        return postDao.getPostById(id);
    }

    @Override
    public Post addPost(PostRequestContext request) {
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        if(request.getScheduledDate() == null || request.getExpirationDate() == null){
            post.setPostTime(LocalDateTime.now());
        } else {
            post.setPostTime(request.getScheduledDate());
            post.setScheduledDate(request.getScheduledDate());
            post.setExpirationDate(request.getExpirationDate());
        }
        
        return postDao.addPost(post);
    }

    @Override
    public boolean editPost(PostRequestContext request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletePostById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User addUser(UserRequestContext request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editUser(UserRequestContext request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteUserById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Hashtag> getHashtags() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Hashtag addHashtag(Hashtag hashtag) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteHashtagById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void validatePostRequest(PostRequestContext request){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.   
    }

    
    /**
     * Takes in a PostRequestContext and calls userDao to return User object
     * associated with the userId.
     * 
     * @param request
     * @return User object associated with id
     */
    private User getUserForRequest(PostRequestContext request){
        return userDao.getUserById(request.getUserId());
    }
}
