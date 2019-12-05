package com.nc.backend.services;

import com.nc.backend.model.HashtagsEntity;
import com.nc.backend.model.PostsEntity;

import java.util.List;

public interface HashtagService {
    Iterable<HashtagsEntity> findAll();
    List<PostsEntity> getPostsByHashtagText(String text);
}
