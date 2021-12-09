/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.blog.data;

import com.sg.blog.models.Post;
import com.sg.blog.service.PostQueryContext;
import java.util.List;

/**
 *
 * @author calebdiaz
 */
public interface BlogPostDao {
    Post addPost(Post post);
    List<Post> getAllPosts();
    List<Post> getAllPosts(PostQueryContext query);
    Post getPostById(int id);
    boolean updatePost(Post post);
    boolean deletePostById(int id);
}
