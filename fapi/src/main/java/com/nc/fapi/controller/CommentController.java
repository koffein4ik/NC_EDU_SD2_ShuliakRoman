package com.nc.fapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}

