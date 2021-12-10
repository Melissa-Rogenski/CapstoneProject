/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.blog.data;

import com.sg.blog.data.BlogHashtagDaoDatabaseImpl.HashtagMapper;
import com.sg.blog.models.Hashtag;
import com.sg.blog.models.Post;
import com.sg.blog.models.User;
import com.sg.blog.service.PostQueryContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author calebdiaz
 */
@Repository
@Profile("db")
public class BlogPostDaoDatabaseImpl implements BlogPostDao {

    @Autowired
    private JdbcTemplate jdbc;

    public BlogPostDaoDatabaseImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    
    @Override
    public Post addPost(Post post) {
    	final String INSERT_POST = "INSERT INTO post(post_time, scheduled_Date, expiration_Date, title, content) "
                + "VALUES(?)";
        jdbc.update(INSERT_POST, 
                post.getPostTime(),
                post.getScheduledDate(),
                post.getExpirationDate(),
                post.getTitle(),
                post.getContent());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        post.setPostId(newId);
        return post;
    }
    

    @Override
    public List<Post> getAllPosts() {
    	final String SELECT_ALL_POSTS = "SELECT * FROM posts";
        return jdbc.query(SELECT_ALL_POSTS, new PostMapper());
    }

    @Override
    public List<Post> getAllPosts(PostQueryContext query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Post getPostById(int id) {
    	try {
            final String SELECT_POST_BY_ID = "SELECT * FROM post WHERE post_Id = ?";
            return jdbc.queryForObject(SELECT_POST_BY_ID, new PostMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public boolean updatePost(Post post) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletePostById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // Private helper methods
    private User getUserForPost(Post post){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private List<Hashtag> getHashtagsForPost(Post post){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void addUserAndHashtagsToPosts(List<Post> posts){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void insertPostHashtag(Post post){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Public implementation of RowMapper
    public static final class PostMapper implements RowMapper<Post>{

        @Override
        public Post mapRow(ResultSet rs, int i) throws SQLException {
        	        	 Post pt = new Post();
        	            pt.setPostId(rs.getInt("post_Id"));
        	            pt.setUserId(rs.getInt("user_Id"));
        	            pt.setPostTime(rs.getTimestamp("post_time").toLocalDateTime());
        	            pt.setScheduledDate(rs.getTimestamp("scheduled_date").toLocalDateTime());
        	            pt.setExpirationDate(rs.getTimestamp("expiration_date").toLocalDateTime());
        	            pt.setTitle(rs.getString("title"));
        	            pt.setContent(rs.getString("content"));
        	             return pt;
        	        
        	             
        	         
       
        	     	
             }
         
        }
    
    }
    

