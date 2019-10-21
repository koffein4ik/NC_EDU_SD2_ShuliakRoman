package com.nc.backend.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "hashtags", schema = "photosquare")
public class HashtagsEntity {
    private int htagId;
    private String text;
    private Integer posts;

    @Id
    @Column(name = "htag_id")
    public int getHtagId() {
        return htagId;
    }

    public void setHtagId(int htagId) {
        this.htagId = htagId;
    }

    @Basic
    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "posts")
    public Integer getPosts() {
        return posts;
    }

    public void setPosts(Integer posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashtagsEntity that = (HashtagsEntity) o;
        return htagId == that.htagId &&
                Objects.equals(text, that.text) &&
                Objects.equals(posts, that.posts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(htagId, text, posts);
    }
}
