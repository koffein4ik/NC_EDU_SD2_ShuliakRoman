package com.nc.fapi.controller;

import com.nc.fapi.model.LoginData;
import com.nc.fapi.model.PostsEntity;
import com.nc.fapi.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    RestTemplate restTemplate;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "uploadimage", method = RequestMethod.POST, headers={"content-type=multipart/form-data"})
    public void uploadImage(@RequestParam("fileKey")MultipartFile[] files) throws IOException{
//        String name = file.getOriginalFilename();
//        System.out.println(name);
//        byte[] bytes = file.getBytes();
//        System.out.println(bytes.length);
        System.out.println(files.length);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "authorization", method = RequestMethod.POST)
    public UserEntity authorize(@RequestBody LoginData loginData) {
        System.out.println(loginData.getLogin());
        System.out.println(loginData.getPassword());
        HttpHeaders headers = new HttpHeaders();
        //headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Content-Type", "application/json");
        HttpEntity<LoginData> httpEntity = new HttpEntity<>(loginData, headers);
        //ResponseEntity<Object> exchange = restTemplate.exchange("http://localhost:8080/api/users/authorization", HttpMethod.POST, httpEntity, Object.class);
        ResponseEntity<UserEntity>  userResponseEntity = restTemplate.postForEntity("http://localhost:8080/api/users/authorization", httpEntity, UserEntity.class);
        //return responseEntity;
        System.out.println(userResponseEntity.getBody());
        return userResponseEntity.getBody();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("getusers")
    public String getUsersList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        return restTemplate.exchange("http://localhost:8080/api/users/show", HttpMethod.GET, httpEntity, String.class).getBody();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "getpost", method = RequestMethod.GET)
    public ResponseEntity<PostsEntity[]> getPost() {
        ResponseEntity<PostsEntity[]> posts = restTemplate.getForEntity("http://localhost:8080/api/posts/show", PostsEntity[].class);
        for(PostsEntity post : posts.getBody()) {
             String folderpath = "src/main/resources/userphotos/users/" + post.getUser().getId() + "/posts/" + post.getPostId();
            //System.out.println(folderpath);
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
