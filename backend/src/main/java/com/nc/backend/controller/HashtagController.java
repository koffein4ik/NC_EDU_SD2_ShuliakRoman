package com.nc.backend.controller;

import com.nc.backend.model.CommentEntity;
import com.nc.backend.model.PostsEntity;
import com.nc.backend.services.HashtagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/hashtags")
public class HashtagController {

    @Autowired
    HashtagService hashtagService;

    @RequestMapping("getpostsbyhashtag/{hashtag}/{page}")
    public List<PostsEntity> getPostsByHashtagText(@PathVariable(name = "hashtag") String hashtag,
                                                   @PathVariable(name = "page") String page) {
        System.out.println(hashtag);
        return hashtagService.getPostsByHashtagText(hashtag, Integer.parseInt(page));
    }
}
