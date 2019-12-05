package com.nc.backend.services;

import com.nc.backend.model.CommentEntity;
import com.nc.backend.model.PostsEntity;
import com.nc.backend.model.SubmitComment;
import com.nc.backend.model.UserEntity;
import com.nc.backend.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

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

    @Override
    public Iterable<CommentEntity> save(CommentEntity commentEntity) {
        commentRepository.save(commentEntity);
        return findAllByPostId(commentEntity.getPost().getPostId());
    }
}
