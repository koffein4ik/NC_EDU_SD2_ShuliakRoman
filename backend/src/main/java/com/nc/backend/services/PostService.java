package com.nc.backend.services;

import com.nc.backend.model.HashtagsEntity;
import com.nc.backend.model.PostHashtagsCount;
import com.nc.backend.model.PostsEntity;
import com.nc.backend.model.UserIdPostDescription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<PostsEntity> findAll(Integer page);

    Optional<PostsEntity> addNewPost(PostsEntity postsEntity);

    PostsEntity getPostEntity(UserIdPostDescription userIdPostDescription);

    List<PostHashtagsCount> getPostHashtagsCount();

    List<PostsEntity> getPostsByUserNickname(String nickname, Integer page);

    List<PostsEntity> getPostsFromSubscriptions(Integer id, Integer page);

    @Transactional
    void deletePostById(Integer id);
}
