package com.nc.backend.services;

import com.nc.backend.model.HashtagsEntity;

public interface HashtagService {
    Iterable<HashtagsEntity> findAll();
}
