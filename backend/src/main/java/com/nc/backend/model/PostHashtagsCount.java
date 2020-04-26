package com.nc.backend.model;

import java.util.Comparator;

public class PostHashtagsCount implements Comparable<PostHashtagsCount> {
    private int postId;
    private int hashtagsCount;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getHashtagsCount() {
        return hashtagsCount;
    }

    public void setHashtagsCount(int hashtagsCount) {
        this.hashtagsCount = hashtagsCount;
    }

    @Override
    public int compareTo(PostHashtagsCount o1) {
        return this.hashtagsCount > o1.getHashtagsCount() ? -1 : 1;
    }
}
