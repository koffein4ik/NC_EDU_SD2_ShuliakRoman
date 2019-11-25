package com.nc.fapi.model;

import java.sql.Timestamp;
import java.util.Objects;

public class LikedislikeEntity {
    private int likedilslikeId;
    private Byte type;
    private Timestamp date;
    private UserEntity user;
    private PostsEntity post;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public PostsEntity getPost() {
        return post;
    }

    public void setPost(PostsEntity post) {
        this.post = post;
    }

    public int getLikedilslikeId() {
        return likedilslikeId;
    }

    public void setLikedilslikeId(int likedilslikeId) {
        this.likedilslikeId = likedilslikeId;
    }


    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LikedislikeEntity that = (LikedislikeEntity) o;
        return likedilslikeId == that.likedilslikeId &&
                Objects.equals(type, that.type) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(likedilslikeId, type, date);
    }
}
