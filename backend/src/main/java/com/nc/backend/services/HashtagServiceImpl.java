package com.nc.backend.services;

import com.nc.backend.model.HashtagsEntity;
import com.nc.backend.repositories.HashtagRepository;
import com.nc.backend.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("hashtagService")
public class HashtagServiceImpl implements HashtagService {
    @Autowired
    HashtagRepository hashtagRepository;

    @Override
    public Iterable<HashtagsEntity> findAll() {
        return hashtagRepository.findAll();
    }
}
