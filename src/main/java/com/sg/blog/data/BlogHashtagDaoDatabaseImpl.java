/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.blog.data;
import com.sg.blog.models.Hashtag;
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
public class BlogHashtagDaoDatabaseImpl implements BlogHashtagDao {

    @Autowired
    private JdbcTemplate jdbc;

    public BlogHashtagDaoDatabaseImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    
    @Override
    public Hashtag addHashtag(Hashtag hashtag) {
    	final String INSERT_HASHTAG = "INSERT INTO hashtag(hashtag) "
                + "VALUES(?)";
        jdbc.update(INSERT_HASHTAG, 
                hashtag.getHashtag());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        hashtag.setHashtagId(newId);
        return hashtag;
    }

    @Override
    public Hashtag getHashtagById(int id) {
    	try {
            final String SELECT_HASHTAG_BY_ID = "SELECT * FROM hashtag WHERE hashtag_Id = ?";
            return jdbc.queryForObject(SELECT_HASHTAG_BY_ID, new HashtagMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Hashtag> getAllHashtags() {
    	final String SELECT_ALL_HASHTAGS = "SELECT * FROM hashtag";
        return jdbc.query(SELECT_ALL_HASHTAGS, new HashtagMapper());
    }

    @Override
    public boolean updateHashtag(Hashtag hashtag) {
    	 final String UPDATE_HASHTAG = "UPDATE hashtag SET hashtag = ?"
        
    			+ "WHERE hashtag_Id= ?;";
         jdbc.update(UPDATE_HASHTAG,
        		 hashtag.getHashtag(),
        		 hashtag.getHashtagId());
         return true;
    }
    @Override
    public boolean deleteHashtagById(int id) {
        
        final String DELETE_HASHTAG = "DELETE FROM hashtag WHERE hashtag_id = ?";
        jdbc.update(DELETE_HASHTAG, id);
        
        return true;
    }
    
    // Public implementation of RowMapper
    
    public static final class HashtagMapper implements RowMapper<Hashtag>{

        @Override
        public Hashtag mapRow(ResultSet rs, int i) throws SQLException {
        	 Hashtag ht = new Hashtag();
             ht.setHashtagId(rs.getInt("hashtag_Id"));
             ht.setHashtag(rs.getString("hashtag"));
             return ht;
        }
    
    }
   
    
}
