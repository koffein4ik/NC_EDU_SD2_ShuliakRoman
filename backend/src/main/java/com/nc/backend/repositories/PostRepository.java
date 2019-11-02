package com.nc.backend.repositories;

import com.nc.backend.model.HashtagsEntity;
import com.nc.backend.model.PostsEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<PostsEntity, Integer> {

}
