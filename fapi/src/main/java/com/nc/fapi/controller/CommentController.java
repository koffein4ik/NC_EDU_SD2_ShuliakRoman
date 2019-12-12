package com.nc.fapi.controller;

import com.nc.fapi.model.CommentEntity;
import com.nc.fapi.model.PostsEntity;
import com.nc.fapi.model.SubmitComment;
import com.nc.fapi.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("api/comments")
public class CommentController {

    @Autowired
    RestTemplate restTemplate;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("getcommentsbypostid/{postId}")
    public String getLikesDislikesByPostId(@PathVariable(name = "postId") String postId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        return restTemplate.exchange("http://localhost:8080/api/comments/getbypostid/" + postId, HttpMethod.GET, httpEntity, String.class).getBody();
    }

    @PreAuthorize("hasAnyRole('ROLE_Admin', 'ROLE_User')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("submitcomment")
    public String submitComment(@RequestBody SubmitComment submitComment) {
        System.out.println(submitComment.getPostId());
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<SubmitComment> httpEntity = new HttpEntity<>(submitComment, headers);
        return restTemplate.exchange("http://localhost:8080/api/comments/submitcomment", HttpMethod.POST, httpEntity, String.class).getBody();
    }

}

