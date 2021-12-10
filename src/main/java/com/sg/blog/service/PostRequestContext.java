/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.blog.service;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Object created to request relevant info for a post record to be added to the database.
 * 
 * @author calebdiaz
 */
public class PostRequestContext {
    private int postId;
    private String title;
    private String content;
    private LocalDateTime scheduledDate = null;
    private LocalDateTime expirationDate = null;
    private int userId;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDateTime scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.postId;
        hash = 89 * hash + Objects.hashCode(this.title);
        hash = 89 * hash + Objects.hashCode(this.content);
        hash = 89 * hash + Objects.hashCode(this.scheduledDate);
        hash = 89 * hash + Objects.hashCode(this.expirationDate);
        hash = 89 * hash + this.userId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PostRequestContext other = (PostRequestContext) obj;
        if (this.postId != other.postId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        if (!Objects.equals(this.scheduledDate, other.scheduledDate)) {
            return false;
        }
        if (!Objects.equals(this.expirationDate, other.expirationDate)) {
            return false;
        }
        return true;
    }

    
    
}
