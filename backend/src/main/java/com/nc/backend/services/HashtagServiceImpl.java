package com.nc.backend.services;

import com.nc.backend.model.HashtagsEntity;
import com.nc.backend.model.PosthathtagsEntity;
import com.nc.backend.model.PostsEntity;
import com.nc.backend.repositories.HashtagRepository;
import com.nc.backend.repositories.PostHashtagRepository;
import com.nc.backend.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service("hashtagService")
public class HashtagServiceImpl implements HashtagService {

    @Autowired
    HashtagRepository hashtagRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostHashtagRepository postHashtagRepository;

    @Override
    public Iterable<HashtagsEntity> findAll() {
        return hashtagRepository.findAll();
    }

    @Override
    public List<PostsEntity> getPostsByHashtagText(String text, Integer page) {
        int size = 2;
        Pageable onepost = PageRequest.of(page, size);
        Optional<HashtagsEntity> byText = hashtagRepository.getByText(text);
        if (byText.isPresent()){
            List<PosthathtagsEntity> posthathtags = postHashtagRepository.findAllByHashtagId(byText.get().getHtagId(), onepost);
            List<PostsEntity> posts = new ArrayList<>();
            for (PosthathtagsEntity posthathtagsEntity: posthathtags) {
                Optional<PostsEntity> post = postRepository.findById(posthathtagsEntity.getPostId());
                if (post.isPresent()) {
                    posts.add(post.get());
                }
            }
            return posts;
        } else {
            return Collections.emptyList();
        }
    }
}
