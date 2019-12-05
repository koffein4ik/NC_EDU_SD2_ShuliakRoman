package com.nc.backend.repositories;

import com.nc.backend.model.UserEntity;
import com.nc.backend.model.UserSubscriptionsEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserSubscriptionsRepository extends PagingAndSortingRepository<UserSubscriptionsEntity, Integer> {
    Iterable<UserSubscriptionsEntity> getAllBySubscriberId(Integer id);

    Iterable<UserSubscriptionsEntity> getAllBySubscribedToId(Integer id);

    Integer countAllBySubscribedTo_Id(Integer id);

    Integer countAllBySubscriber_Id(Integer id);

    Optional<UserSubscriptionsEntity> getBySubscriber_IdAndSubscribedTo_Id(Integer subscriberId, Integer SubscribedToId);

    @Transactional
    @Modifying
    @Query(value = "Delete from UserSubscriptionsEntity us where us.subscriber.id= :subscriberId and us.subscribedTo.id = :subscribeToId")
    void removeSubscription(@Param("subscriberId") Integer subscriberId, @Param("subscribeToId") Integer subscribedToId);
}
