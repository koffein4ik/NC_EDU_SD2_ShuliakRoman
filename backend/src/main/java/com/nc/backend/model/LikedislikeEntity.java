package com.nc.backend.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "likedislike", schema = "photosquare")
public class LikedislikeEntity {
    private int likedilslikeId;
    private Byte type;
    private Timestamp date;

    @Id
    @Column(name = "likedilslike_id")
    public int getLikedilslikeId() {
        return likedilslikeId;
    }

    public void setLikedilslikeId(int likedilslikeId) {
        this.likedilslikeId = likedilslikeId;
    }

    @Basic
    @Column(name = "type")
    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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
