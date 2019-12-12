package com.nc.backend.repositories;

import com.nc.backend.model.HashtagsEntity;
import com.nc.backend.model.PostsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends PagingAndSortingRepository<PostsEntity, Integer> {
    Optional<PostsEntity> findFirstByOrderByPostIdDesc();

    List<PostsEntity> findAllByUserId(Integer userId, Pageable pageable);

    Page<PostsEntity> findAll(Pageable page);

    @Query("from PostsEntity pe join UserSubscriptionsEntity ue on pe.user.id=ue.subscribedTo.id and ue.subscriber.id=:subscriberid ")
    List<PostsEntity> findAllFromSubscriptions(Integer subscriberid, Pageable pageable);

//    @Query
//    Iterable<PostsEntity> findAllByUserIdWithPageAndOffset()


//    @Transactional
//    @Modifying
//    @Query(value = "Delete from PostsEntity p where p.postId= :postId")
//    void deleteByPostId(@Param("subscriberId") Integer postId);

    void deleteByPostId(Integer postId);
}
