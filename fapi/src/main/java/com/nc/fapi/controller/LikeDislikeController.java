package com.nc.fapi.controller;

import com.nc.fapi.model.LikeDislikeToPut;
import com.nc.fapi.model.LikedislikeEntity;
import com.nc.fapi.model.PostsEntity;
import com.nc.fapi.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

@RestController
@RequestMapping("api/likesdislikes")
public class LikeDislikeController {

    @Autowired
    RestTemplate restTemplate;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("getliesdislikesbypostid/{postId}")
    public String getLikesDislikesByPostId(@PathVariable(name = "postId") String postId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        return restTemplate.exchange("http://localhost:8080/api/likesdislikes/getbypostid/" + postId, HttpMethod.GET, httpEntity, String.class).getBody();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("getlikesdislikescountbypostid/{postId}")
    public String getLikesDislikesCountByPostId(@PathVariable(name = "postId") String postId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        return restTemplate.exchange("http://localhost:8080/api/likesdislikes/countbypostid/" + postId, HttpMethod.GET, httpEntity, String.class).getBody();
    }

    @PreAuthorize("hasAnyRole('ROLE_Admin', 'ROLE_User')")
    @CrossOrigin(origins =  "http://localhost:4200")
    @RequestMapping(value = "putlikedislike", method = RequestMethod.PUT)
    public String putLikeDislike(@RequestBody String data) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Object obj = authentication.getPrincipal();
            System.out.println(authentication.getAuthorities());
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>(data, headers);
        return restTemplate.exchange("http://localhost:8080/api/likesdislikes/putlikedislike", HttpMethod.PUT, httpEntity, String.class).getBody();
    }

    @PreAuthorize("hasAnyRole('ROLE_Admin', 'ROLE_User')")
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "removeuserreaction", method = RequestMethod.DELETE)
    public String removeUserReaction(@RequestBody String removeData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntityData = new HttpEntity<>(removeData, headers);
        return restTemplate.exchange("http://localhost:8080/api/likesdislikes/removeuserreaction", HttpMethod.DELETE, httpEntityData, String.class).getBody();
    }

    @RequestMapping(value = "getuserreaction", method = RequestMethod.POST)
    public String getUserReaction(@RequestBody String data) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntityData = new HttpEntity<>(data, headers);
        return restTemplate.exchange("http://localhost:8080/api/likesdislikes/getuserreaction", HttpMethod.POST, httpEntityData, String.class).getBody();
    }
}
