/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.blog.service;

import com.sg.blog.models.Hashtag;
import com.sg.blog.models.Post;
import com.sg.blog.models.User;
import java.util.List;

/**
 *
 * @author calebdiaz
 */
public interface BlogServiceLayer {
    
    /**
     * Calls the postDao to return a list of all Posts with
     * the expired field set to false.
     * 
     * @return List<Post> unexpired posts
     */
    List<Post> home();
    
    /**
     * Takes in a PostQueryContext object and calls postDao
     * to return a list of all posts matching the conditions
     * determined by the fields of the query.
     * 
     * @param query - object containing fields by which to query the database
     * @return List<Post> post records matching query
     */
    List<Post> getPosts(PostQueryContext query);
    
    /**
     * Takes in an id corresponding to a post record and calls
     * postDao to return Post object associated with the id.
     * 
     * @param id of post record to get
     * @return Post object or null
     */
    Post getPostById(int id);
    
    /**
     * Takes in a PostRequestContext object and calls postDao method
     * to create a post record and Post object matching fields of object.
     * 
     * @param request
     * @return Post object created or null
     */
    Post addPost(PostRequestContext request);
    
    /**
     * Takes in a PostRequestContext object with fields of post record to edit
     * and calls postDao to update the record.
     * 
     * @param request
     * @return true if successful, false if not
     */
    boolean editPost(PostRequestContext request);
    
    /**
     * Takes in an id corresponding to a post record and calls postDao
     * to delete the record.
     * 
     * @param id
     * @return true if successful, false if not
     */
    boolean deletePostById(int id);
    
    /**
     * Calls userDao to return a list of User objects constructed from 
     * all user records in the database.
     * 
     * @return List<User>
     */
    List<User> getUsers();
    
    /**
     * Takes in a UserRequestContext object and calls userDao method
     * to create a user record and User object matching fields of object.
     * 
     * @param request
     * @return User object created from inserted user record or null
     */
    User addUser(UserRequestContext request);
    
    /**
     * Takes in a UserRequestContext object with fields of user record to edit
     * and calls userDao to update the record.
     * 
     * @param request
     * @return true if successful, false if not
     */
    boolean editUser(UserRequestContext request);
    
    /**
     * Takes in an id corresponding to a user record and calls userDao
     * to delete the record.
     * 
     * @param id
     * @return true if successful, false if not
     */
    boolean deleteUserById(int id);
    
    /**
     * Calls hashtagDao to return a list of Hashtag objects constructed from
     * all hashtag records in the database.
     * 
     * @return List<Hashtag>
     */
    List<Hashtag> getHashtags();
    
    /**
     * Takes in a Hashtag object and calls hashtagDao method
     * to create a hashtag record and Hashtag object matching fields of object.
     * 
     * @param hashtag
     * @return Hashtag object created or null
     */
    Hashtag addHashtag(Hashtag hashtag);
    
    /**
     * Takes in an id corresponding to a hashtag record and calls hashtagDao
     * to delete the record.
     * 
     * @param id
     * @return true if successful, false if not
     */
    boolean deleteHashtagById(int id);

}
