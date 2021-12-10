/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.blog.data;

import com.sg.blog.models.Hashtag;
import java.util.List;

/**
 *
 * @author calebdiaz
 */
public interface BlogHashtagDao {
    
    /**
     * Takes in a Hashtag object and persists to database.
     * 
     * @param hashtag - object to be persisted
     * @return persisted object
     */
    Hashtag addHashtag(Hashtag hashtag);
    
    /**
     * Takes in a hashtag id PK and queries database for record matching id.
     * 
     * @param id - id of record to query for
     * @return Hashtag object created from fields returned by query
     */
    Hashtag getHashtagById(int id);
    
    /**
     * Returns a list of all Hashtag objects created from database query.
     * 
     * @return list of Hashtag objects
     */
    List<Hashtag> getAllHashtags();
    
    /**
     * Takes in a Hashtag object and updates database record matching id PK.
     * 
     * @param hashtag - record to be updated
     * @return true if successful, false if not
     */
    boolean updateHashtag(Hashtag hashtag);
    
    /**
     * Takes in a hashtag id PK and deletes record matching id.
     * 
     * @param id - id of record to be deleted from database
     * @return true if successful, false if not
     */
    boolean deleteHashtagById(int id);

}
