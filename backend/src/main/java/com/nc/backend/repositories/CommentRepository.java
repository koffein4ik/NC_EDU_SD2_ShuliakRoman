package com.nc.backend.repositories;

import com.nc.backend.model.CommentEntity;
import com.nc.backend.model.PostsEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<CommentEntity, Integer> {
    Iterable<CommentEntity> findAllByPost(PostsEntity post);
}
