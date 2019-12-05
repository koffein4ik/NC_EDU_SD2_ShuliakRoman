package com.nc.backend.services;

import com.nc.backend.model.SubscriptionData;
import com.nc.backend.model.UserEntity;

import java.util.List;

public interface UserSubscriptionsService {
    List<UserEntity> getAllSubscriptionsBySubscriberId(Integer id);

    List<UserEntity> getAllSubscribersByUserId(Integer id);

    SubscriptionData subscribeToUser(Integer subscriberId, Integer subscribeToUserId);

    SubscriptionData unsubscribe(Integer subscriberId, Integer subscribeToUserId);

    SubscriptionData getSubscriptionData(Integer id, Integer currUserId);
}
