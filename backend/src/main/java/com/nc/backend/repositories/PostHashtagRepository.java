package com.nc.backend.repositories;

import com.nc.backend.model.PosthathtagsEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostHashtagRepository extends PagingAndSortingRepository<PosthathtagsEntity, Integer> {
    List<PosthathtagsEntity> findAllByHashtagId(Integer hashtagId, Pageable page);
}
