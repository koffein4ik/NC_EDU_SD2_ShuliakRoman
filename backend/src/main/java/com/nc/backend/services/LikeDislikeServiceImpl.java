package com.nc.backend.services;

import com.nc.backend.model.LikeDislikeCount;
import com.nc.backend.model.LikedislikeEntity;
import com.nc.backend.model.PostsEntity;
import com.nc.backend.repositories.LikeDislikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("likeDislikeService")
public class LikeDislikeServiceImpl implements LikeDislikeService {
    @Autowired
    LikeDislikeRepository likeDislikeRepository;

    @Override
    public Iterable<LikedislikeEntity> findAllByPost(PostsEntity post) {
        return likeDislikeRepository.findAllByPost(post);
    }

    @Override
    public Optional<LikedislikeEntity> getLikesDislikesByPostIdAndUserId(Integer postId, Integer userId) {
        return likeDislikeRepository.getLikeDislikeByPostIdAndUserId(postId, userId);
    }

    @Override
    public LikeDislikeCount removeLikeDislikeByPostIdAndUserId(Integer postId, Integer userId) {
        likeDislikeRepository.removeLikeDislike(postId, userId);
        return countAllLikesDislikesByPostId(postId);
    }

    @Override
    public LikeDislikeCount save(LikedislikeEntity likedislikeEntity) {
        Optional<LikedislikeEntity> ldEntity = likeDislikeRepository.getLikeDislikeByPostIdAndUserId(likedislikeEntity.
                                        getPost().getPostId(), likedislikeEntity.getUser().getId());
        if (ldEntity.isPresent()) {
            likeDislikeRepository.removeLikeDislike(likedislikeEntity.
                    getPost().getPostId(), likedislikeEntity.getUser().getId());
        }
        likeDislikeRepository.save(likedislikeEntity);
        return countAllLikesDislikesByPostId(likedislikeEntity.getPost().getPostId());
    }

    @Override
    public Iterable<LikedislikeEntity> findAllByPostId(Integer postId) {
        PostsEntity post = new PostsEntity();
        post.setPostId(postId);
        return findAllByPost(post);
    }

    @Override
    public LikeDislikeCount countAllLikesDislikesByPostId(Integer postId) {
        Iterable<LikedislikeEntity> likedislikeEntities = findAllByPostId(postId);
        LikeDislikeCount likeDislikeCount = new LikeDislikeCount();
        for (LikedislikeEntity likedislikeEntity : likedislikeEntities) {
            if (likedislikeEntity.getType() == 1) {
                likeDislikeCount.likes++;
            }
            else {
                likeDislikeCount.dislikes++;
            }
        }
        return likeDislikeCount;
    }

}
