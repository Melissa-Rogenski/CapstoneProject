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
    /**
     * Takes in a Post object and persists to database.
     * 
     * @param post - object to be persisted
     * @return Post persisted object
     */
    Post addPost(Post post);
    
    /**
     * Returns a list of Post objects created from database query.
     * 
     * @return List<Post> of all post records in database
     */
    List<Post> getAllPosts();
    
    /**
     * Takes in a PostQueryContext object and executes appropriate query corresponding
     * to the fields in the query. Returns a list of Post objects created from records
     * matching query.
     * 
     * @param query - indicates specific conditions to query by
     * @return List<Post> of post records matching query
     */
    List<Post> getAllPosts(PostQueryContext query);
    
    /**
     * Takes in the id corresponding to a post record and queries the database
     * for the record matching that id. Returns a post object constructed from
     * record from database.
     * 
     * @param id - id PK of post record in database
     * @return Post object constructed from database record
     */
    Post getPostById(int id);
    
    /**
     * Takes in a Post object and updates record in database matching id PK
     * 
     * @param post - Post object with updated fields
     * @return true if successful, false if not
     */
    boolean updatePost(Post post);
    
    /**
     * Takes in id corresponding to a post record and deletes record corresponding
     * to id PK.
     * 
     * @param id - of object to be deleted from database
     * @return true if successful, false if not
     */
    boolean deletePostById(int id);
}
