/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.blog.data;

import com.sg.blog.data.BlogHashtagDaoDatabaseImpl.HashtagMapper;
import com.sg.blog.data.BlogUserDaoDatabaseImpl.UserMapper;
import com.sg.blog.models.Hashtag;
import com.sg.blog.models.Post;
import com.sg.blog.models.User;
import com.sg.blog.service.PostQueryContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Post addPost(Post post) {
    	final String INSERT_POST = "INSERT INTO post(post_time, scheduled_Date, expiration_Date, expired, title, content, user_Id) "
                + "VALUES(?,?,?,?,?,?,?)";
        jdbc.update(INSERT_POST, 
                post.getPostTime(),
                post.getScheduledDate(),
                post.getExpirationDate(),
                post.isExpired(),
                post.getTitle(),
                post.getContent(),
                post.getUser().getUserId());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        post.setPostId(newId);
        
        insertPostHashtag(post);
        
        return post;
    }
    

    @Override
    public List<Post> getAllPosts() {
    	final String SELECT_ALL_POSTS = "SELECT * FROM post "
                + "WHERE expired = false;";
        List<Post> posts = jdbc.query(SELECT_ALL_POSTS, new PostMapper());
        addUserAndHashtagsToPosts(posts);
        return posts;
    }

    @Override
    public List<Post> getAllPosts(PostQueryContext query) {
        List<Post> posts = new ArrayList<>();
        if(!query.getSearchBar().isBlank()){
            if(query.getHashtagId() != 0){
                final String SELECT_ALL_POSTS_BY_QUERY = "SELECT * FROM post p "
                        + "JOIN postHashtag ph ON p.post_Id = ph.post_Id "
                        + "JOIN hashtag h ON ph.hashtag_Id = h.hashtag_Id "
                        + "WHERE h.hashtag_Id = ? "
                        + "AND (title LIKE ? OR content LIKE ?) "
                        + "AND (post_time BETWEEN ? AND ?)";
                posts = jdbc.query(SELECT_ALL_POSTS_BY_QUERY, new PostMapper(), query.getHashtagId(),
                        "%"+query.getSearchBar()+"%", "%"+query.getSearchBar()+"%", 
                        query.getMinDate(), query.getMaxDate());
            } else {
                final String SELECT_ALL_POSTS_BY_QUERY = "SELECT * FROM post p "
                            + "WHERE title LIKE ? "
                            + "OR content LIKE ? "
                            + "AND (post_time BETWEEN ? AND ?)";
                    posts = jdbc.query(SELECT_ALL_POSTS_BY_QUERY, new PostMapper(), 
                            "%"+query.getSearchBar()+"%", "%"+query.getSearchBar()+"%", 
                            query.getMinDate(), query.getMaxDate());
            }
        } else if(query.getHashtagId() != 0){
                final String SELECT_ALL_POSTS_BY_QUERY = "SELECT * FROM post p "
                        + "JOIN postHashtag ph ON p.post_Id = ph.post_Id "
                        + "JOIN hashtag h ON ph.hashtag_Id = h.hashtag_Id "
                        + "WHERE h.hashtag_Id = ? "
                        + "AND (post_time BETWEEN ? AND ?)";
                posts = jdbc.query(SELECT_ALL_POSTS_BY_QUERY, new PostMapper(), query.getHashtagId(),
                        query.getMinDate(), query.getMaxDate());
        } else {
                 final String SELECT_ALL_POSTS_BY_QUERY = "SELECT * FROM post p "
                        + "WHERE (post_time BETWEEN ? AND ?)";
                posts = jdbc.query(SELECT_ALL_POSTS_BY_QUERY, new PostMapper(), 
                        query.getMinDate(), query.getMaxDate());           
        }
        addUserAndHashtagsToPosts(posts);
        return posts;
    }

    @Override
    public Post getPostById(int id) {
    	try {
            final String SELECT_POST_BY_ID = "SELECT * FROM post WHERE post_Id = ?";
            Post post = jdbc.queryForObject(SELECT_POST_BY_ID, new PostMapper(), id);
            post.setUser(getUserForPost(post));
            post.setHashtags(getHashtagsForPost(post));
            return post;
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public boolean updatePost(Post post) {
        final String UPDATE_POST = "UPDATE post "
               + "SET title = ?, content = ?, scheduled_date = ?, expiration_date = ?, expired = ?  WHERE post_Id = ? ";
        jdbc.update(UPDATE_POST,
                post.getTitle(),
                post.getContent(),
                Timestamp.valueOf(post.getScheduledDate()),
                Timestamp.valueOf(post.getExpirationDate()),
                post.isExpired(),
                post.getPostId());
        
        insertPostHashtag(post);
        
        return true;
    }

    @Override
    public boolean deletePostById(int id) {
        final String DELETE_POST_HASHTAG = "DELETE FROM postHashtag ph "
                + "WHERE ph.post_Id = ? ";
        jdbc.update(DELETE_POST_HASHTAG, id);
        
        final String DELETE_POST = "DELETE FROM post WHERE post_Id = ? ";
        jdbc.update(DELETE_POST, id);
        
        return true;
    }
    
    // Private helper methods
    private User getUserForPost(Post post){
        final String SELECT_USER_FOR_POST = "SELECT u.* FROM user u "
                + "JOIN post p ON u.user_Id = p.user_Id WHERE p.post_Id = ? ";
        return jdbc.queryForObject(SELECT_USER_FOR_POST, new UserMapper(), post.getPostId());
    }
    
    private List<Hashtag> getHashtagsForPost(Post post){
        final String SELECT_HASHTAGS_FOR_POST = "SELECT h.* FROM hashtag h "
                + "JOIN postHashtag ph ON h.hashtag_Id = ph.hashtag_Id WHERE ph.post_Id = ? ";
        return jdbc.query(SELECT_HASHTAGS_FOR_POST, new HashtagMapper(), post.getPostId());
    }
    
    private void addUserAndHashtagsToPosts(List<Post> posts){
        posts.stream().forEach((p) -> {
            p.setUser(getUserForPost(p));
            p.setHashtags(getHashtagsForPost(p));
        });
    }
    
    private void insertPostHashtag(Post post){
        final String INSERT_POST_HASHTAG = "INSERT INTO postHashtag"
                + "(post_Id, hashtag_Id) VALUES(?,?)";
        for(Hashtag h : post.getHashtags()) {
            jdbc.update(INSERT_POST_HASHTAG, post.getPostId(), h.getHashtagId());
        }
    }

    // Public implementation of RowMapper
    public static final class PostMapper implements RowMapper<Post>{

        @Override
        public Post mapRow(ResultSet rs, int i) throws SQLException {
        	        	 Post pt = new Post();
        	            pt.setPostId(rs.getInt("post_Id"));
        	            pt.setPostTime(rs.getTimestamp("post_time").toLocalDateTime());
                            try{
        	            pt.setScheduledDate(rs.getTimestamp("scheduled_date").toLocalDateTime());
                            } catch (Exception e){}
                            try{
        	            pt.setExpirationDate(rs.getTimestamp("expiration_date").toLocalDateTime());
                            } catch (Exception e){}
                            pt.setExpired(rs.getBoolean("expired"));
        	            pt.setTitle(rs.getString("title"));
        	            pt.setContent(rs.getString("content"));
        	            return pt;   	
             }
        }
    }
    

