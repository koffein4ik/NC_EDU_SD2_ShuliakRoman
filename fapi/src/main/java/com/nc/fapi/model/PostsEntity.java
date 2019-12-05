package com.nc.fapi.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PostsEntity {
    private int postId;
    private UserEntity user;
    private String description;
    private Timestamp date;
    private String location;
    private Set<String> photoURIs = new HashSet<>();

    private Set<HashtagsEntity> hashtags = new HashSet<>();

    public Set<String> getPhotoURIs() {
        return photoURIs;
    }

    public void setPhotoURIs(Set<String> photoURIs) {
        this.photoURIs = photoURIs;
    }

    public Set<HashtagsEntity> getHashtags() {
        return hashtags;
    }

    public void setHashtags(Set<HashtagsEntity> hashtags) {
        this.hashtags = hashtags;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }


    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostsEntity that = (PostsEntity) o;
        return postId == that.postId &&
                Objects.equals(description, that.description) &&
                Objects.equals(date, that.date) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, description, date, location);
    }
}
