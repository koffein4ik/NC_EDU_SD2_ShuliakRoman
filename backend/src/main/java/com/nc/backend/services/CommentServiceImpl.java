package com.nc.backend.services;

import com.nc.backend.model.CommentEntity;
import com.nc.backend.model.PostsEntity;
import com.nc.backend.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public Iterable<CommentEntity> findAllByPost(PostsEntity post) {
        return commentRepository.findAllByPost(post);
    }

    @Override
    public Iterable<CommentEntity> findAllByPostId(Integer postId) {
        PostsEntity post = new PostsEntity();
        post.setPostId(postId);
        return findAllByPost(post);
    }
}
