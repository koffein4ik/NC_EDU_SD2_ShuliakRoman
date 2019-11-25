package com.nc.fapi.model;

import java.util.Objects;

public class PosthathtagsEntity {
    private int posthashtagId;

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
