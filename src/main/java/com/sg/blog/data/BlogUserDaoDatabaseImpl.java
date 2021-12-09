/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.blog.data;

import com.sg.blog.models.Hashtag;
import com.sg.blog.models.User;
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
public class BlogUserDaoDatabaseImpl implements BlogUserDao {
    
    @Autowired
    private JdbcTemplate jdbc;

    public BlogUserDaoDatabaseImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public User addUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUserById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getAllUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteUserById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // Public implementation of RowMapper
    public static final class UserMapper implements RowMapper<User>{

        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
        	 User us = new User();
             us.setUserId((rs.getInt("user_Id")));
             us.setFirstName((rs.getString("first_Name")));
             us.setLastName(rs.getString("last_Name"));
             us.setEmail(rs.getString("email"));
             us.setPassword(rs.getString("password"));
             return us;
        
        }
    
    }
}
