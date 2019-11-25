package com.nc.fapi.controller;

import com.nc.fapi.model.PostsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("api/posts")

public class PostController {

    @Autowired
    RestTemplate restTemplate;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "getallposts", method = RequestMethod.GET)
    public ResponseEntity<PostsEntity[]> getPost() {
        ResponseEntity<PostsEntity[]> posts = restTemplate.getForEntity("http://localhost:8080/api/posts/show", PostsEntity[].class);
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
        System.out.println(posts.getBody());
        return posts;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(
            path = "images/{userId}/{postId}/{photoId}",
            method = RequestMethod.GET,
            produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable String userId, @PathVariable String postId, @PathVariable String photoId) throws IOException {
        System.out.println(System.getProperty("user.dir"));
        String photosPath = "src/main/resources/userphotos/users/" + userId + "/posts/" + postId + "/" + photoId;
        System.out.println(photosPath);
        RandomAccessFile file = new RandomAccessFile(photosPath, "r");
        byte[] byteArrray = new byte[(int)file.length()];
        file.readFully(byteArrray);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(byteArrray);
    }
}
