package com.nc.backend.controller;

import com.nc.backend.model.CommentEntity;
import com.nc.backend.model.PostsEntity;
import com.nc.backend.model.SubmitComment;
import com.nc.backend.model.UserEntity;
import com.nc.backend.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("api/comments")
public class CommentController {
    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/getbypostid/{postid}", method = RequestMethod.GET)
    public Iterable<CommentEntity> getAllCommentsByPostId(@PathVariable(name = "postid") String postId) {
        return commentService.findAllByPostId(Integer.parseInt(postId));
    }

    @PostMapping("submitcomment")
    public Iterable<CommentEntity> submitComment(@RequestBody SubmitComment submitComment) {
        System.out.println(submitComment.getPostId());
        CommentEntity commentEntity = new CommentEntity();
        PostsEntity post = new PostsEntity();
        post.setPostId(submitComment.getPostId());
        commentEntity.setPost(post);
        UserEntity user = new UserEntity();
        user.setId(submitComment.getUserId());
        commentEntity.setUser(user);
        Date date = new Date();
        commentEntity.setDate(new Timestamp((date.getTime())));
        commentEntity.setText(submitComment.getText());
        return commentService.save(commentEntity);
    }
}
