package com.nc.backend.repositories;

import com.nc.backend.model.CommentEntity;
import com.nc.backend.model.PostsEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<CommentEntity, Integer> {
    Iterable<CommentEntity> findAllByPost(PostsEntity post);
}
