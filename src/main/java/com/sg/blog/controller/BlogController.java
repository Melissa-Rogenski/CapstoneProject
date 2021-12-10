/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.blog.controller;

import com.sg.blog.models.Hashtag;
import com.sg.blog.models.Post;
import com.sg.blog.models.User;
import com.sg.blog.service.BlogServiceLayer;
import com.sg.blog.service.InvalidDateException;
import com.sg.blog.service.PostQueryContext;
import com.sg.blog.service.PostRequestContext;
import com.sg.blog.service.UserRequestContext;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author calebdiaz
 */
@RestController
@RequestMapping("/api/")
public class BlogController {
    
    private BlogServiceLayer service;

    public BlogController(BlogServiceLayer service) {
        this.service = service;
    }
    
    @GetMapping("/home/index")
    public List<Post> home(){
        return service.home();
    }
    
    @GetMapping("/home/posts")
    public ResponseEntity<List<Post>> getPosts(@RequestBody PostQueryContext query){
        List<Post> result = service.getPosts(query);
        if (result == null) {
            return new ResponseEntity("There was an error processing your request.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/post/details/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable int id){
        Post result = service.getPostById(id);
        if (result == null) {
            return new ResponseEntity("There was an error processing your request.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/admin/posts")
    public ResponseEntity<List<Post>> getAdminPosts(@RequestBody PostQueryContext query){
        List<Post> result = service.getPosts(query);
        if (result == null) {
            return new ResponseEntity("There was an error processing your request.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(result);
    }
    
    @PostMapping("/admin/posts")
    public ResponseEntity<Post> addPost(@RequestBody PostRequestContext request) throws InvalidDateException {
        Post result = service.addPost(request);
        if (result == null) {
            return new ResponseEntity("There was an error processing your request.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(result);
        
    }
    
    @PostMapping("/admin/post-{post_Id}/hashtag/{id}")
    public ResponseEntity addHashtagToPost(@PathVariable int post_Id, @PathVariable int id){
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        if (service.getPostById(id) == null) {
            response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (service.addHashtagToPost(id, post_Id)) {
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return response;
    }
    
    @PutMapping("/admin/editpost/{id}")
    public ResponseEntity editPost(@RequestBody PostRequestContext request, @PathVariable int id) throws InvalidDateException{
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        request.setPostId(id);
        if (service.getPostById(id) == null) {
            response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (service.editPost(request)) {
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return response;
    }
    
    @DeleteMapping("/admin/deletepost/{id}")
    public ResponseEntity deletePost(@PathVariable int id){
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        if (service.getPostById(id) == null) {
            response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (service.deletePostById(id)) {
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return response;
    }
    
    @GetMapping("/admin/users")
    public List<User> getUsers(){
        return service.getUsers();
    }
    
    @PostMapping("/admin/adduser")
    public ResponseEntity<User> addUser(@RequestBody UserRequestContext request){
        User result = service.addUser(request);
        if (result == null) {
            return new ResponseEntity("There was an error processing your request.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(result);
    }
    
    @PutMapping("/admin/edituser/{id}")
    public ResponseEntity editUser(@RequestBody UserRequestContext request, @PathVariable int id){
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        request.setUserId(id);
        if (service.editUser(request)) {
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return response;
    }
    
    @DeleteMapping("/admin/edituser/{id}")
    public ResponseEntity deleteUser(@PathVariable int id){
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        if (service.deleteUserById(id)) {
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return response;
    }
    
    @GetMapping("/admin/hashtags")
    public List<Hashtag> getHashtags(){
        return service.getHashtags();
    }
    
    @PostMapping("/admin/addhashtag")
    public ResponseEntity<Hashtag> addHashtag(@RequestBody Hashtag hashtag){
        Hashtag result = service.addHashtag(hashtag);
        if (result == null) {
            return new ResponseEntity("There was an error processing your request.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(result);
    }
    
    @DeleteMapping("/admin/deletehashtag/{id}")
    public ResponseEntity deleteHashtag(@PathVariable int id){
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        if (service.deleteHashtagById(id)) {
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return response;
    }

}
