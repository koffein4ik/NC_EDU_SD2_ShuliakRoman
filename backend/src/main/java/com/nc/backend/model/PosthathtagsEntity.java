package com.nc.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "posthathtags", schema = "photosquare")
public class PosthathtagsEntity {
    private int posthashtagId;

    @Id
    @Column(name = "posthashtag_id")
    public int getPosthashtagId() {
        return posthashtagId;
    }

    public void setPosthashtagId(int posthashtagId) {
        this.posthashtagId = posthashtagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PosthathtagsEntity that = (PosthathtagsEntity) o;
        return posthashtagId == that.posthashtagId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(posthashtagId);
    }
}
