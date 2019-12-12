package com.nc.fapi.controller;

import com.nc.fapi.model.PostsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("api/hashtags")
public class HashtagController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("getpostsbyhashtag/{hashtag}/{page}")
    public ResponseEntity<PostsEntity[]> getPostsByHashtagText(@PathVariable(name = "hashtag") String hashtag,
                                                               @PathVariable(name = "page") String page) {
        ResponseEntity<PostsEntity[]> posts = restTemplate.getForEntity("http://localhost:8080/api/hashtags/" +
                "getpostsbyhashtag/" + hashtag + "/" + page, PostsEntity[].class);
        if (posts.getBody() != null) {
            for(PostsEntity post : posts.getBody()) {
                String folderpath = "src/main/resources/userphotos/users/" + post.getUser().getId() + "/posts/" + post.getPostId();
                File folder = new File(folderpath);
                File[] listOfFiles = folder.listFiles();
                Set<String> photos = new HashSet<>();
                for (int i = 0; i < listOfFiles.length; i++) {
                    System.out.println(listOfFiles[i].getName());
                    photos.add(listOfFiles[i].getName());
                }
                post.setPhotoURIs(photos);
            }
        }
        return posts;
    }
}
