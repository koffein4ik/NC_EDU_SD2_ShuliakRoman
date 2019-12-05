package com.nc.backend.controller;

import com.nc.backend.model.SubscriptionData;
import com.nc.backend.model.UserEntity;
import com.nc.backend.model.UserSubscription;
import com.nc.backend.services.UserSubscriptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usersubscriptions")
public class UserSubscriptionsController {

    @Autowired
    UserSubscriptionsService userSubscriptionsService;

    @GetMapping("getsubscriptionsdata/{userid}/{curruserid}")
    SubscriptionData getSubscriptionData(@PathVariable(name = "userid") String userId,
                                         @PathVariable(name = "curruserid") String currUserId) {
        return userSubscriptionsService.getSubscriptionData(Integer.parseInt(userId), Integer.parseInt(currUserId));
    }

    @DeleteMapping("unsubscribe")
    public SubscriptionData unsubscribe(@RequestBody UserSubscription userSubscription) {
        return userSubscriptionsService.unsubscribe(userSubscription.getSubscriberId(),
                userSubscription.getSubscribedToId());
    }

    @PostMapping("subscribe")
    public SubscriptionData subscribe(@RequestBody UserSubscription userSubscription) {
        return userSubscriptionsService.subscribeToUser(userSubscription.getSubscriberId(), userSubscription.getSubscribedToId());
    }
}
