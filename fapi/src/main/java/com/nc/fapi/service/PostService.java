package com.nc.fapi.service;

import com.nc.fapi.model.PostsEntity;

import java.util.List;

public interface PostService {
    PostsEntity[] getPhotosForPosts(PostsEntity[] posts);
    PostsEntity getPhotosFromPost(PostsEntity post);
}
