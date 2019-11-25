package com.nc.backend.services;

import com.nc.backend.model.LikeDislikeCount;
import com.nc.backend.model.LikedislikeEntity;
import com.nc.backend.model.PostsEntity;

import java.util.Optional;

public interface LikeDislikeService {
    Iterable<LikedislikeEntity> findAllByPost(PostsEntity post);

    Iterable<LikedislikeEntity> findAllByPostId(Integer postId);

    LikeDislikeCount countAllLikesDislikesByPostId(Integer postId);

    LikeDislikeCount save(LikedislikeEntity likedislikeEntity);

    Optional<LikedislikeEntity> getLikesDislikesByPostIdAndUserId(Integer postId, Integer userId);

    LikeDislikeCount removeLikeDislikeByPostIdAndUserId(Integer postId, Integer userId);
}
