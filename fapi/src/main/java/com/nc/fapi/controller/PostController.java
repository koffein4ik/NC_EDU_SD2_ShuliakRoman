package com.nc.fapi.controller;

import com.nc.fapi.model.PostHashtagsCount;
import com.nc.fapi.model.PostsEntity;
import com.nc.fapi.model.UserIdPostDescription;
import com.nc.fapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
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

import static org.apache.tomcat.util.http.fileupload.FileUtils.deleteDirectory;

@RestController
@RequestMapping("api/posts")

public class PostController {

    private final RestTemplate restTemplate = new RestTemplate();

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "getallposts/{page}", method = RequestMethod.GET)
    public PostsEntity[] getPosts(@PathVariable(name = "page") String page) {
        PostsEntity[] posts = restTemplate.getForObject("http://localhost:8080/api/posts/show/" + page, PostsEntity[].class);
        return postService.getPhotosForPosts(posts);
    }

    @PreAuthorize("hasAnyRole('ROLE_Admin', 'ROLE_User')")
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

    @PreAuthorize("hasAnyRole('ROLE_Admin', 'ROLE_User')")
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
        boolean dirCreated = file.mkdirs();
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

    @RequestMapping("getpostsbynickname/{nickname}/{page}")
    public PostsEntity[] getPostsByNickname(@PathVariable(name = "nickname") String nickname,
                                                            @PathVariable(name = "page") String page) {
        String url = "http://localhost:8080/api/posts/" +
                "getpostsbyusernickname/" + nickname + "/" + page;
        PostsEntity[] posts = restTemplate.getForObject(url, PostsEntity[].class);
        return postService.getPhotosForPosts(posts);
    }

    @PreAuthorize("hasAnyRole('ROLE_Admin', 'ROLE_User')")
    @RequestMapping("getpostsfromsubscriptions/{id}/{page}")
    public PostsEntity[] getPostsFromSubscriptions(@PathVariable(name = "id") String userId,
                                                                   @PathVariable(name = "page") String page) {
        PostsEntity[] posts = restTemplate.getForObject("http://localhost:8080/api/posts/" +
                "getpostsfromsubscriptions/" + Integer.parseInt(userId) + "/" + page, PostsEntity[].class);
        return postService.getPhotosForPosts(posts);
    }

    @PreAuthorize("hasRole('ROLE_Admin')")
    @GetMapping(path = "statistics")
    public PostHashtagsCount[] getPostHashtagsCount() {
        return restTemplate.getForObject("http://localhost:8080/api/posts/getposthashtagscount", PostHashtagsCount[].class);
    }
}
