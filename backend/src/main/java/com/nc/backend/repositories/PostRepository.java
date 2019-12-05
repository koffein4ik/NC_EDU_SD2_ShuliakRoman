package com.nc.backend.repositories;

import com.nc.backend.model.HashtagsEntity;
import com.nc.backend.model.PostsEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PostRepository extends PagingAndSortingRepository<PostsEntity, Integer> {
    Optional<PostsEntity> findFirstByOrderByPostIdDesc();

    Iterable<PostsEntity> findAllByUserId(Integer userId);

//    @Transactional
//    @Modifying
//    @Query(value = "Delete from PostsEntity p where p.postId= :postId")
//    void deleteByPostId(@Param("subscriberId") Integer postId);

    void deleteByPostId(Integer postId);
}
