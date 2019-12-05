package com.nc.backend.services;

import com.nc.backend.model.HashtagsEntity;
import com.nc.backend.model.PostsEntity;
import com.nc.backend.repositories.HashtagRepository;
import com.nc.backend.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service("hashtagService")
public class HashtagServiceImpl implements HashtagService {
    @Autowired
    HashtagRepository hashtagRepository;

    @Override
    public Iterable<HashtagsEntity> findAll() {
        return hashtagRepository.findAll();
    }

    @Override
    public List<PostsEntity> getPostsByHashtagText(String text) {
        Optional<HashtagsEntity> byText = hashtagRepository.getByText(text);
        if (byText.isPresent()){
            return new ArrayList<PostsEntity>(byText.get().getPosts());
        } else {
            return Collections.emptyList();
        }
    }
}
