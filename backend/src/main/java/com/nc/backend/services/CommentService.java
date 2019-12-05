package com.nc.backend.services;

import com.nc.backend.model.CommentEntity;
import com.nc.backend.model.PostsEntity;
import com.nc.backend.model.SubmitComment;

public interface CommentService {
    Iterable<CommentEntity> findAllByPost(PostsEntity post);
    Iterable<CommentEntity> findAllByPostId(Integer postId);
    Iterable<CommentEntity> save(CommentEntity commentEntity);
}
