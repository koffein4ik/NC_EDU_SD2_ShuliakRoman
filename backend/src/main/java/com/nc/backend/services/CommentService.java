package com.nc.backend.services;

import com.nc.backend.model.CommentEntity;
import com.nc.backend.model.PostsEntity;

public interface CommentService {
    Iterable<CommentEntity> findAllByPost(PostsEntity post);
    Iterable<CommentEntity> findAllByPostId(Integer postId);
}
