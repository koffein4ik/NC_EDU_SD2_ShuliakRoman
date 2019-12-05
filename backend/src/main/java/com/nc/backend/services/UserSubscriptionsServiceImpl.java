package com.nc.backend.services;

import com.nc.backend.model.SubscriptionData;
import com.nc.backend.model.UserEntity;
import com.nc.backend.model.UserSubscriptionsEntity;
import com.nc.backend.repositories.UserRepository;
import com.nc.backend.repositories.UserSubscriptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("UserSubscriptionsService")
public class UserSubscriptionsServiceImpl implements UserSubscriptionsService {

    @Autowired
    UserSubscriptionsRepository userSubscriptionsRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserEntity> getAllSubscriptionsBySubscriberId(Integer id) {
        Iterable<UserSubscriptionsEntity> userSubscriptionsEntities = userSubscriptionsRepository.getAllBySubscriberId(id);
        List<UserEntity> subscriptions = new ArrayList<>();
        for (UserSubscriptionsEntity u : userSubscriptionsEntities) {
            subscriptions.add(u.getSubscribedTo());
        }
        return subscriptions;
    }

    @Override
    public List<UserEntity> getAllSubscribersByUserId(Integer id) {
        Iterable<UserSubscriptionsEntity> userSubscribers = userSubscriptionsRepository.getAllBySubscribedToId(id);
        List<UserEntity> subscribers = new ArrayList<>();
        for (UserSubscriptionsEntity u : userSubscribers) {
            subscribers.add(u.getSubscriber());
        }
        return subscribers;
    }

    @Override
    public SubscriptionData getSubscriptionData(Integer id, Integer currUserId) {
        SubscriptionData subscriptionData = new SubscriptionData();
        subscriptionData.setSubscriptions(userSubscriptionsRepository.countAllBySubscriber_Id(id));
        subscriptionData.setSubscribers(userSubscriptionsRepository.countAllBySubscribedTo_Id(id));
        Optional<UserSubscriptionsEntity> userSubscriptionsEntity = userSubscriptionsRepository.
                getBySubscriber_IdAndSubscribedTo_Id(currUserId, id);
        subscriptionData.setCurrUserSubscribed(userSubscriptionsEntity.isPresent());
        return subscriptionData;
    }

    @Override
    public SubscriptionData subscribeToUser(Integer subscriberId, Integer subscribeToUserId) {
        UserSubscriptionsEntity userSubscriptionsEntity = new UserSubscriptionsEntity();
        Optional<UserEntity> subscriber = userRepository.findById(subscriberId);
        if (subscriber.isPresent()) {
            userSubscriptionsEntity.setSubscriber(subscriber.get());
        }
        Optional<UserEntity> subscribedTo = userRepository.findById(subscribeToUserId);
        if (subscribedTo.isPresent()) {
            userSubscriptionsEntity.setSubscribedTo(subscribedTo.get());
        }
        userSubscriptionsRepository.save(userSubscriptionsEntity);
        return getSubscriptionData(subscribeToUserId, subscriberId);
    }

    @Override
    public SubscriptionData unsubscribe(Integer subscriberId, Integer subscribedToUserId) {
        userSubscriptionsRepository.removeSubscription(subscriberId, subscribedToUserId);
        return getSubscriptionData(subscribedToUserId, subscriberId);
    }
}
