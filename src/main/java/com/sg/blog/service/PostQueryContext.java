/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.blog.service;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author calebdiaz
 */
public class PostQueryContext {
    private String searchBar = "";
    private LocalDateTime minDate = LocalDateTime.parse("2000-01-01T00:00:00");
    private LocalDateTime maxDate = LocalDateTime.parse("2030-01-01T00:00:00");
    private int hashtagId = 0;

    public String getSearchBar() {
        return searchBar;
    }

    public void setSearchBar(String searchBar) {
        this.searchBar = searchBar;
    }

    public LocalDateTime getMinDate() {
        return minDate;
    }

    public void setMinDate(LocalDateTime minDate) {
        this.minDate = minDate;
    }

    public LocalDateTime getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(LocalDateTime maxDate) {
        this.maxDate = maxDate;
    }

    public int getHashtagId() {
        return hashtagId;
    }

    public void setHashtagId(int hashtagId) {
        this.hashtagId = hashtagId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.searchBar);
        hash = 29 * hash + Objects.hashCode(this.minDate);
        hash = 29 * hash + Objects.hashCode(this.maxDate);
        hash = 29 * hash + this.hashtagId;
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
        final PostQueryContext other = (PostQueryContext) obj;
        if (this.hashtagId != other.hashtagId) {
            return false;
        }
        if (!Objects.equals(this.searchBar, other.searchBar)) {
            return false;
        }
        if (!Objects.equals(this.minDate, other.minDate)) {
            return false;
        }
        if (!Objects.equals(this.maxDate, other.maxDate)) {
            return false;
        }
        return true;
    }
    
    

}
