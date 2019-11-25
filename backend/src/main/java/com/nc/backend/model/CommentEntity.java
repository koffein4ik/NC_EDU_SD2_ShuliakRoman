package com.nc.backend.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "comment", schema = "photosquare")
public class CommentEntity {
    private int commentId;
    private String text;
    private Timestamp date;
    private UserEntity user;
    private PostsEntity post;

    @ManyToOne
    @JoinColumn(name="user_id")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name="post_id")
    public PostsEntity getPost() {
        return post;
    }

    public void setPost(PostsEntity post) {
        this.post = post;
    }

    @Id
    @Column(name = "comment_id")
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
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
    @Column(name = "date")
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
        CommentEntity that = (CommentEntity) o;
        return commentId == that.commentId &&
                Objects.equals(text, that.text) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, text, date);
    }
}
