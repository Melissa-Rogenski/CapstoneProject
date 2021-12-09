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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Hashtag getHashtagById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Hashtag> getAllHashtags() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateHashtag(Hashtag hashtag) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteHashtagById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
