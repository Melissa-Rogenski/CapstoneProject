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
    public Post addPost(PostRequestContext request) throws InvalidDateException {
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setUser(getUserFromRequest(request));
        if(request.getScheduledDate() == null || request.getExpirationDate() == null){
            post.setPostTime(LocalDateTime.now());
        } else {
            validatePostRequest(request);
            post.setPostTime(request.getScheduledDate());
            post.setScheduledDate(request.getScheduledDate());
            post.setExpirationDate(request.getExpirationDate());
        }
        
        return postDao.addPost(post);
    }

    @Override
    public boolean editPost(PostRequestContext request) throws InvalidDateException {
        Post post = new Post();
        post.setPostId(request.getPostId());
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setUser(getUserFromRequest(request));
        if(request.getScheduledDate() == null || request.getExpirationDate() == null){
            post.setPostTime(LocalDateTime.now());
        } else {
            validatePostRequest(request);
            post.setPostTime(request.getScheduledDate());
            post.setScheduledDate(request.getScheduledDate());
            post.setExpirationDate(request.getExpirationDate());
        }
        
        return postDao.updatePost(post);
    }

    @Override
    public boolean deletePostById(int id) {
        if(postDao.getPostById(id) == null){
            return false;
        } else {
            return postDao.deletePostById(id);
        }
    }

    @Override
    public List<User> getUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User addUser(UserRequestContext request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        
        return userDao.addUser(user);
    }

    @Override
    public boolean editUser(UserRequestContext request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        
        return userDao.updateUser(user);
    }

    @Override
    public boolean deleteUserById(int id) {
        if(userDao.getUserById(id) == null){
            return false;
        } else {
            return userDao.deleteUserById(id);
        }
    }

    @Override
    public List<Hashtag> getHashtags() {
        return hashtagDao.getAllHashtags();
    }

    @Override
    public Hashtag addHashtag(Hashtag hashtag) {
        return hashtagDao.addHashtag(hashtag);
    }

    @Override
    public boolean deleteHashtagById(int id) {
        if(hashtagDao.getHashtagById(id) == null){
            return false;
        } else {
            return hashtagDao.deleteHashtagById(id);
        }
    }
    
    private void validatePostRequest(PostRequestContext request) throws InvalidDateException {
        if(request.getScheduledDate().compareTo(request.getExpirationDate()) > 0){
            throw new InvalidDateException("Post date cannot be after expiration date.");
        }
    }

    
    /**
     * Takes in a PostRequestContext and calls userDao to return User object
     * associated with the userId.
     * 
     * @param request
     * @return User object associated with id
     */
    private User getUserFromRequest(PostRequestContext request){
        return userDao.getUserById(request.getUserId());
    }
}
