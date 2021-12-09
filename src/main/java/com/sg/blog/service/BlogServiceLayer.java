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
    List<Post> home();
    List<Post> getPosts(PostQueryContext query);
    Post getPostById(int id);
    Post addPost(PostRequestContext request);
    boolean editPost(PostRequestContext request);
    boolean deletePostById(int id);
    List<User> getUsers();
    User addUser(UserRequestContext request);
    boolean editUser(UserRequestContext request);
    boolean deleteUserById(int id);
    List<Hashtag> getHashtags();
    Hashtag addHashtag(Hashtag hashtag);
    boolean deleteHashtagById(int id);

}
