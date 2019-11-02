package com.nc.backend.repositories;

import com.nc.backend.model.HashtagsEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface HashtagRepository extends PagingAndSortingRepository<HashtagsEntity, Integer> {

}