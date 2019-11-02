package com.nc.backend.controller;

import com.nc.backend.model.HashtagsEntity;
import com.nc.backend.model.PostsEntity;
import com.nc.backend.repositories.HashtagRepository;
import com.nc.backend.repositories.PostRepository;
import com.nc.backend.services.HashtagService;
import com.nc.backend.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/posts")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping("show")
    Iterable<PostsEntity> getAllPosts() {
        return postService.findAll();
    }

}
