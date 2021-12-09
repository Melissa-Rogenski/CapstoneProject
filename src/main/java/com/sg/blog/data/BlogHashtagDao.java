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
    Hashtag addHashtag(Hashtag hashtag);
    Hashtag getHashtagById(int id);
    List<Hashtag> getAllHashtags();
    boolean updateHashtag(Hashtag hashtag);
    boolean deleteHashtagById(int id);

}
