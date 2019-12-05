package com.nc.backend.services;

import com.nc.backend.model.HashtagsEntity;
import com.nc.backend.model.PostsEntity;
import com.nc.backend.model.UserIdPostDescription;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Iterable<PostsEntity> findAll();

    Optional<PostsEntity> addNewPost(PostsEntity postsEntity);

    PostsEntity getPostEntity(UserIdPostDescription userIdPostDescription);

    List<PostsEntity> getPostsByUserNickname(String nickname);

    List<PostsEntity> getPostsFromSubscriptions(Integer id);

    @Transactional
    void deletePostById(Integer id);
}
