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

    private final RestTemplate restTemplate = new RestTemplate();

    @RequestMapping("getcommentsbypostid/{postId}")
    public CommentEntity[] getCommentsByPostId(@PathVariable(name = "postId") String postId) {
        return restTemplate.getForObject("http://localhost:8080/api/comments/getbypostid/" + postId, CommentEntity[].class);
    }

    @PreAuthorize("hasAnyRole('ROLE_Admin', 'ROLE_User')")
    @PostMapping("submitcomment")
    public CommentEntity[] submitComment(@RequestBody SubmitComment submitComment) {
        return restTemplate.postForObject("http://localhost:8080/api/comments/submitcomment", submitComment, CommentEntity[].class);
    }

}

