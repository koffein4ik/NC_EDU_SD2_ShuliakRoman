package com.nc.backend.services;

import com.nc.backend.model.*;
import com.nc.backend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service("postService")
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    HashtagRepository hashtagRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserSubscriptionsService userSubscriptionsService;

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    LikeDislikeRepository likeDislikeRepository;

    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<PostsEntity> findAll(Integer page) {
        Pageable currPage = PageRequest.of(page, 1);
        return postRepository.findAll(currPage).getContent();
    }

    @Override
    public Optional<PostsEntity> addNewPost(PostsEntity postsEntity) {
        postRepository.save(postsEntity);
        return postRepository.findFirstByOrderByPostIdDesc();
    }

    @Override
    public PostsEntity getPostEntity(UserIdPostDescription userIdPostDescription) {
        PostsEntity postsEntity = new PostsEntity();
        postsEntity.setDescription(userIdPostDescription.getPostDescription());
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userIdPostDescription.getUserId());
        postsEntity.setUser(userEntity);
        Date date = new Date();
        postsEntity.setDate(new Timestamp(date.getTime()));
        String description = userIdPostDescription.getPostDescription();
        for (int i = 0; i < description.length(); i++) {
            if (description.charAt(i) == '#') {
                int j = i + 1;
                while ((j < description.length()) && (description.substring(j, j + 1).matches("^[a-zA-Z0-9]+"))) {
                    j++;
                }
                i++;
                String hashtag = description.substring(i, j);
                HashtagsEntity hashtagsEntity = new HashtagsEntity();
                hashtagsEntity.setText(hashtag);
                int hTagId = 0;
                Optional<HashtagsEntity> hashtagFromDb = hashtagRepository.getByText(hashtag);
                if (hashtagFromDb.isPresent()) {
                    hashtagsEntity = hashtagFromDb.get();
                    postsEntity.getHashtags().add(hashtagsEntity);
                } else {
                    hashtagRepository.save(hashtagsEntity);
                    hashtagFromDb = hashtagRepository.getFirstByOrderByHtagIdDesc();
                    if (hashtagFromDb.isPresent()) {
                        hashtagsEntity = hashtagFromDb.get();
                        postsEntity.getHashtags().add(hashtagsEntity);
                    } else {
//                        System.out.println("No hashtag with such id");
                    }
                }
//                System.out.println(postsEntity.getHashtags().toArray().toString());
//                System.out.println(hashtag);
            }
        }
        return postsEntity;
    }

    @Override
    public List<PostsEntity> getPostsByUserNickname(String nickname, Integer page) {
        Pageable onepost = PageRequest.of(page, 1);
        Optional<UserEntity> user = userRepository.findByNickname(nickname);
        if (user.isPresent()) {
            List<PostsEntity> posts = postRepository.findAllByUserId(user.get().getId(), onepost);
            return posts;
        }
        else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<PostsEntity> getPostsFromSubscriptions(Integer id, Integer page) {
        Pageable pageable = PageRequest.of(page, 2);
        return postRepository.findAllFromSubscriptions(id, pageable);
    }

    @Override
    public void deletePostById(Integer id) {
        Optional<PostsEntity> currPost = postRepository.findById(id);
        if (currPost.isPresent()) {
            currPost.get().setHashtags(new HashSet<>());
            postRepository.save(currPost.get());
            commentRepository.deleteAll(commentRepository.findAllByPost(currPost.get()));
            likeDislikeRepository.deleteAll(likeDislikeRepository.findAllByPost(currPost.get()));
            reportRepository.deleteReportsByPostId(id);
            postRepository.deleteByPostId(id);
        }
    }

}
