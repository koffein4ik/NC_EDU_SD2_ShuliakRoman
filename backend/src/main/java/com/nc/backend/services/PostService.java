package com.nc.backend.services;

import com.nc.backend.model.HashtagsEntity;
import com.nc.backend.model.PostsEntity;

public interface PostService {
    public Iterable<PostsEntity> findAll();
}
