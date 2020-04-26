package com.nc.fapi.service;

import com.nc.fapi.model.PostsEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PostServiceImpl implements PostService {
    @Override
    public PostsEntity[] getPhotosForPosts(PostsEntity[] posts) {
        for (PostsEntity post : posts) {
            String folderpath = "src/main/resources/userphotos/users/" + post.getUser().getId() + "/posts/" + post.getPostId();
            File folder = new File(folderpath);
            if (folder.exists()) {
                File[] listOfFiles = folder.listFiles();
                Set<String> photos = new HashSet<>();
                for (File file : listOfFiles) {
                    photos.add(file.getName());
                }
                post.setPhotoURIs(photos);
            }
        }
        return posts;
    }

    @Override
    public PostsEntity getPhotosFromPost(PostsEntity post) {
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
        return post;
    }
}
