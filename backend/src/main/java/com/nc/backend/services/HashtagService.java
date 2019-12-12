package com.nc.backend.services;

import com.nc.backend.model.HashtagsEntity;
import com.nc.backend.model.PostsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HashtagService {
    Iterable<HashtagsEntity> findAll();
    List<PostsEntity> getPostsByHashtagText(String text, Integer page);
}
