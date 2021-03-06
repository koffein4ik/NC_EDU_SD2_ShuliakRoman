package com.nc.backend.repositories;

import com.nc.backend.model.HashtagsEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface HashtagRepository extends PagingAndSortingRepository<HashtagsEntity, Integer> {
    Optional<HashtagsEntity> getByText(String text);
    Optional<HashtagsEntity> getFirstByOrderByHtagIdDesc();
}