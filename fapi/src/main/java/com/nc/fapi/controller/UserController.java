package com.nc.fapi.controller;

import com.nc.fapi.model.*;
import com.nc.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;

    private final RestTemplate restTemplate = new RestTemplate();


    @GetMapping("getuserbynickname/{nickname}")
    public UserViewModel getUserViewModelByNickname(@PathVariable(name = "nickname") String nickname) {
        return this.userService.getUserViewModelByNickname(nickname);
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping(value = "regnewuser")
    public UserViewModel regNewUser(@RequestBody UserEntity userEntity) {
        return userService.save(userEntity);
    }

    @PreAuthorize("hasAnyRole('ROLE_Admin', 'ROLE_User')")
    @GetMapping("checkusertoken")
    public HttpStatus checkToken() {
        return HttpStatus.OK;
    }

    @PreAuthorize("hasRole('ROLE_Admin')")
    @GetMapping("getallusers/{page}")
    public UserViewModel[] getAllUsers(@PathVariable(name = "page") String page) {
        return restTemplate.getForObject("http://localhost:8080/api/users/getallusers/" + page, UserViewModel[].class);
    }

    @PreAuthorize("hasAnyRole('ROLE_Admin', 'ROLE_User')")
    @PostMapping("updateuserinfo")
    public ResponseEntity<UserEntity> updateUserInfo(@RequestBody UserEntity userEntity) {
        return restTemplate.postForEntity("http://localhost:8080/api/users/updateuserinfo", userEntity, UserEntity.class);
    }

    @PreAuthorize("hasRole('ROLE_Admin')")
    @GetMapping("blockuser/{userid}")
    public void blockUser(@PathVariable(name = "userid") String userId) {
        restTemplate.getForEntity("http://localhost:8080/api/users/blockuser/" + userId, String.class);
    }

    @PreAuthorize("hasRole('ROLE_Admin')")
    @GetMapping("unblockuser/{userid}")
    public void unblockUser(@PathVariable(name = "userid") String userId) {
        restTemplate.getForEntity("http://localhost:8080/api/users/unblockuser/" + userId, String.class);
    }

    @PreAuthorize("hasAnyRole('ROLE_Admin', 'ROLE_User')")
    @RequestMapping(value = "uploadavatar", method = RequestMethod.POST, headers = {"content-type=multipart/form-data"})
    public HttpStatus uploadAvatar(@RequestParam("fileKey") MultipartFile avatar, @RequestParam("userId") String userId) throws IOException {
        String avatarPath = "src/main/resources/userphotos/users/" + userId + "/avatar/";
        File file = new File(avatarPath);
        if (file.exists()) {
            Path filePath = Paths.get(avatarPath + "avatar.jpg");
            try (OutputStream os = Files.newOutputStream(filePath)) {
                os.write(avatar.getBytes());
            }
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }

//    @RequestMapping(
//            path = "images/{userId}/{postId}/{photoId}",
//            method = RequestMethod.GET,
//            produces = MediaType.IMAGE_PNG_VALUE)
//    public ResponseEntity<byte[]> getImage(@PathVariable String userId, @PathVariable String postId, @PathVariable String photoId) throws IOException {
//        System.out.println(System.getProperty("user.dir"));
//        String photosPath = "src/main/resources/userphotos/users/" + userId + "/posts/" + postId + "/" + photoId;
//        System.out.println(photosPath);
//        RandomAccessFile file = new RandomAccessFile(photosPath, "r");
//        byte[] byteArrray = new byte[(int)file.length()];
//        file.readFully(byteArrray);
//        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(byteArrray);
//    }

    @GetMapping(path = "getuseravatar/{userid}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getUserAvatar(@PathVariable(name="userid") String userId) throws IOException {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(userService.getUserAvatar(Integer.parseInt(userId)));
    }

}
