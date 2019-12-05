package com.nc.fapi.controller;

import com.nc.fapi.model.PostsEntity;
import com.nc.fapi.model.UserIdPostDescription;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import static org.apache.tomcat.util.http.fileupload.FileUtils.deleteDirectory;

@RestController
@RequestMapping("api/posts")

public class PostController {

    @Autowired
    RestTemplate restTemplate;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "getallposts", method = RequestMethod.GET)
    public ResponseEntity<PostsEntity[]> getPost() {
        ResponseEntity<PostsEntity[]> posts = restTemplate.getForEntity("http://localhost:8080/api/posts/show", PostsEntity[].class);
        if (posts.getBody() != null) {
            getImagesToPosts(posts.getBody());
        }
        return posts;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("deletepost/{postid}/{userid}")
    public void deletePostById(@PathVariable(name = "postid") String postId,
                               @PathVariable(name = "userid") String userId) {
        restTemplate.delete("http://localhost:8080/api/posts/deletepost/" + postId);
        String dir = "src/main/resources/userphotos/users/" + userId + "/posts/" + postId;
        File directory = new File(dir);
        if (directory.exists()) {
            try {
                deleteDirectory(directory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(
            path = "images/{userId}/{postId}/{photoId}",
            method = RequestMethod.GET,
            produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable String userId, @PathVariable String postId, @PathVariable String photoId) throws IOException {
        String photosPath = "src/main/resources/userphotos/users/" + userId + "/posts/" + postId + "/" + photoId;
        RandomAccessFile file = new RandomAccessFile(photosPath, "r");
        byte[] byteArrray = new byte[(int) file.length()];
        file.readFully(byteArrray);
        file.close();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(byteArrray);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "addnewpost", method = RequestMethod.POST, headers = {"content-type=multipart/form-data"})
    public void uploadImage(@RequestParam("fileKey") MultipartFile[] files, @RequestParam("userId") String userId,
                            @RequestParam("postDescription") String postDescription) throws IOException {
        UserIdPostDescription userIdPostDescription = new UserIdPostDescription();
        userIdPostDescription.setPostDescription(postDescription);
        userIdPostDescription.setUserId(Integer.parseInt(userId));
        ResponseEntity<PostsEntity> responseEntity = restTemplate.postForEntity("http://localhost:8080/api/posts/addnewpost", userIdPostDescription, PostsEntity.class);
        PostsEntity postsEntity = responseEntity.getBody();
        String photosPath = "src/main/resources/userphotos/users/" + postsEntity.getUser().getId() + "/posts/" +
                postsEntity.getPostId() + "/";
        File file = new File(photosPath);
        boolean dirCreated = file.mkdir();
        if (dirCreated) {
            System.out.println("Dir created");
            for (MultipartFile multipartFile : files) {
                Path filePath = Paths.get(photosPath + multipartFile.getOriginalFilename());
                System.out.println(filePath.toAbsolutePath().toString());
                try (OutputStream os = Files.newOutputStream(filePath)) {
                    os.write(multipartFile.getBytes());
                }
            }
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("getpostsbynickname/{nickname}")
    public ResponseEntity<PostsEntity[]> getPostsByNickname(@PathVariable(name = "nickname") String nickname) {
        ResponseEntity<PostsEntity[]> posts = restTemplate.getForEntity("http://localhost:8080/api/posts/" +
                "getpostsbyusernickname/" + nickname, PostsEntity[].class);
        if (posts.getBody() != null) {
            getImagesToPosts(posts.getBody());
        }
        return posts;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("getpostsfromsubscriptions/{id}")
    public ResponseEntity<PostsEntity[]> getPostsFromSubscriptions(@PathVariable(name = "id") String userId) {
        ResponseEntity<PostsEntity[]> posts = restTemplate.getForEntity("http://localhost:8080/api/posts/" +
                "getpostsfromsubscriptions/" + Integer.parseInt(userId), PostsEntity[].class);
        if (posts.getBody() != null) {
            getImagesToPosts(posts.getBody());
        }
        return posts;
    }

    public PostsEntity[] getImagesToPosts(PostsEntity[] posts) {
        for (PostsEntity post : posts) {
            String folderpath = "src/main/resources/userphotos/users/" + post.getUser().getId() + "/posts/" + post.getPostId();
            File folder = new File(folderpath);
            if (folder.exists()) {
                File[] listOfFiles = folder.listFiles();
                Set<String> photos = new HashSet<>();
                for (int i = 0; i < listOfFiles.length; i++) {
                    photos.add(listOfFiles[i].getName());
                }
                post.setPhotoURIs(photos);
            }
        }
        return posts;
    }
}
