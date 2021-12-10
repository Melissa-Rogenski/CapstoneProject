/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.blog.data;
import com.sg.blog.data.BlogHashtagDaoDatabaseImpl.HashtagMapper;
import com.sg.blog.models.User;
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
public class BlogUserDaoDatabaseImpl implements BlogUserDao {
    
    @Autowired
    private JdbcTemplate jdbc;

    public BlogUserDaoDatabaseImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public User addUser(User user) {
    	final String INSERT_USER = "INSERT INTO user(first_Name, last_Name, email, password) "
                + "VALUES(?,?,?,?)";
        jdbc.update(INSERT_USER, 
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        user.setUserId(newId);
        return user;
    }

    @Override
    public User getUserById(int id) {
    	try {
            final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE user_Id = ?";
            return jdbc.queryForObject(SELECT_USER_BY_ID, new UserMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
    	final String SELECT_ALL_USERS = "SELECT * FROM user";
        return jdbc.query(SELECT_ALL_USERS, new UserMapper());
    }

    @Override
    public boolean updateUser(User user) {
    	final String UPDATE_USER = "UPDATE user SET first_Name = ?, last_Name = ?, email = ?, password= ? "
    			+ "WHERE user_Id = ?";
         jdbc.update(UPDATE_USER,
        		 user.getFirstName(),
        		 user.getLastName(),
        		 user.getEmail(),
        		 user.getPassword(),
                         user.getUserId());
         return true;
    }

    @Override
    public boolean deleteUserById(int id) {
    	 final String DELETE_USER = "DELETE FROM user WHERE user_id = ?";
         jdbc.update(DELETE_USER, id);
         
         return true;
     }
    
    
    // Public implementation of RowMapper

public  static final class UserMapper implements RowMapper<User>{

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

