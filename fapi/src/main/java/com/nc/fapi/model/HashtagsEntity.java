package com.nc.fapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HashtagsEntity {
    private int htagId;
    private String text;

    private Set<PostsEntity> posts = new HashSet<>();

    public Set<PostsEntity> getPosts() {
        return posts;
    }

    public void setPosts(Set<PostsEntity> posts) {
        this.posts = posts;
    }

    public int getHtagId() {
        return htagId;
    }

    public void setHtagId(int htagId) {
        this.htagId = htagId;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashtagsEntity that = (HashtagsEntity) o;
        return htagId == that.htagId &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(htagId, text);
    }
}
