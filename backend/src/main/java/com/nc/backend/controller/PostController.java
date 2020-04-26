package com.nc.backend.controller;

import com.nc.backend.model.HashtagsEntity;
import com.nc.backend.model.PostHashtagsCount;
import com.nc.backend.model.PostsEntity;
import com.nc.backend.model.UserIdPostDescription;
import com.nc.backend.repositories.HashtagRepository;
import com.nc.backend.repositories.PostHashtagRepository;
import com.nc.backend.repositories.PostRepository;
import com.nc.backend.services.HashtagService;
import com.nc.backend.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/posts")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping("show/{page}")
    Iterable<PostsEntity> getAllPosts(@PathVariable(name = "page") String page) {
        return postService.findAll(Integer.parseInt(page));
    }

    @PostMapping("addnewpost")
    PostsEntity addNewPost(@RequestBody UserIdPostDescription userIdPostDescription) {
        Optional<PostsEntity> postsEntity = postService.addNewPost(postService.getPostEntity(userIdPostDescription));
        return postsEntity.orElseGet(PostsEntity::new);
    }

    @RequestMapping("getpostsbyusernickname/{nickname}/{page}")
    public List<PostsEntity> getPostsByUserNickname(@PathVariable(name = "nickname") String nickname,
                                                    @PathVariable(name = "page") String page) {
        return postService.getPostsByUserNickname(nickname, Integer.parseInt(page));
    }

    @DeleteMapping("deletepost/{postid}")
    public void deletePostById(@PathVariable(name = "postid") String postId) {
        postService.deletePostById(Integer.parseInt(postId));
    }

    @RequestMapping("getpostsfromsubscriptions/{id}/{page}")
    public List<PostsEntity> getPostsByUserSubscriptions(@PathVariable(name = "id") String id,
                                                         @PathVariable(name = "page") String page) {
        return postService.getPostsFromSubscriptions(Integer.parseInt(id), Integer.parseInt(page));
    }

    @GetMapping("getposthashtagscount")
    public List<PostHashtagsCount> getPostHashtagsCount() {
        return this.postService.getPostHashtagsCount();
    }

}
