package com.nc.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "posts", schema = "photosquare")
public class PostsEntity {
    private int postId;
    private UserEntity user;
    private String description;
    private Timestamp date;
    private String location;

    private Set<HashtagsEntity> hashtags = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "posthashtags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "hashtag_id")
    )
    @JsonManagedReference
    public Set<HashtagsEntity> getHashtags() {
        return hashtags;
    }

    public void setHashtags(Set<HashtagsEntity> hashtags) {
        this.hashtags = hashtags;
    }

    @ManyToOne
    @JoinColumn(name="user_id")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "post_id")
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "location")
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
        return postId == that.postId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, description, date, location);
    }
}
