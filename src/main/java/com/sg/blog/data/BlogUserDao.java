/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.blog.data;

import com.sg.blog.models.User;
import java.util.List;

/**
 *
 * @author calebdiaz
 */
public interface BlogUserDao {
    
    /**
     * Takes in a User object and persists it the database.
     * 
     * @param user object to be persisted
     * @return User object
     */
    User addUser(User user);
    
    /**
     * Takes in an id PK and queries the database for a record
     * matching the PK. Constructs a User object from returned record
     * and returns the object.
     * 
     * @param id - of object to query for
     * @return User object constructed from record
     */
    User getUserById(int id);
    
    /**
     * Queries the database for all user records and returns a list
     * of User object constructed from database records.
     * 
     * @return List<User> of User objects
     */
    List<User> getAllUsers();
    
    /**
     * Takes in a User object with updated fields and updates database 
     * record matching id PK.
     * 
     * @param user Object with updated fields
     * @return true if successful, false if not
     */
    boolean updateUser(User user);
<<<<<<< HEAD
    void deleteUserById(int id);
=======
    
    /**
     * Takes in an id PK and deletes record from database corresponding to PK
     * 
     * @param id
     * @return 
     */
    boolean deleteUserById(int id);
>>>>>>> b77f482fef7171b195b6890d567b89d2e1e180fd

}
