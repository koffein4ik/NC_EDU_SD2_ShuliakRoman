package com.nc.backend.controller;

import com.nc.backend.model.*;
import com.nc.backend.services.LikeDislikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("api/likesdislikes")
public class LikeDislikeController {

    @Autowired
    LikeDislikeService likeDislikeService;

    @RequestMapping(value = "/getbypostid/{postid}", method = RequestMethod.GET)
    public Iterable<LikedislikeEntity> getAllLikesDislikesByPostId(@PathVariable(name = "postid") String postId) {
        return likeDislikeService.findAllByPostId(Integer.parseInt(postId));
    }

    @RequestMapping(value = "/countbypostid/{postid}", method = RequestMethod.GET)
    public LikeDislikeCount countLikesDislikesByPostId(@PathVariable(name = "postid") String postId) {
        return likeDislikeService.countAllLikesDislikesByPostId(Integer.parseInt(postId));
    }

    @RequestMapping(value = "putlikedislike", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public LikeDislikeCount putLikeDislike(@RequestBody LikeDislikeToPut likeDislikeToPut) {
        LikedislikeEntity likedislikeEntity = new LikedislikeEntity();
        PostsEntity post = new PostsEntity();
        post.setPostId(likeDislikeToPut.getPostId());
        likedislikeEntity.setPost(post);
        UserEntity user = new UserEntity();
        user.setId(likeDislikeToPut.getUserId());
        likedislikeEntity.setUser(user);
        if (likeDislikeToPut.getType()) {
            likedislikeEntity.setType((byte) 1);
        }
        else {
            likedislikeEntity.setType((byte) 0);
        }
        Date date = new Date();
        likedislikeEntity.setDate(new Timestamp(date.getTime()));
        return likeDislikeService.save(likedislikeEntity);
    }

    @RequestMapping(value = "getuserreaction", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Optional getUserReaction(@RequestBody PostUserId postUserId) {
        return likeDislikeService.getLikesDislikesByPostIdAndUserId(postUserId.getPostId(),postUserId.getUserId());
    }

    @DeleteMapping("removeuserreaction")
    public LikeDislikeCount removeUserReaction(@RequestBody PostUserId postUserId) {
        return likeDislikeService.removeLikeDislikeByPostIdAndUserId(postUserId.getPostId(), postUserId.getUserId());
    }
}
