package com.nc.fapi.controller;

import com.nc.fapi.model.SubscriptionData;
import com.nc.fapi.model.UserEntity;
import com.nc.fapi.model.UserSubscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("api/subscriptions")
public class UserSubscriptions {

    @Autowired
    RestTemplate restTemplate;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("getsubscribersbyuserid/{id}")
    ResponseEntity<UserEntity[]> getSubscribersById(@PathVariable(name = "id") String id) {
        return restTemplate.getForEntity("http://localhost:8080/api/usersubscriptions/" +
                "getusersubscribersbyid/" + id, UserEntity[].class);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("getsubscriptionsbyuserid/{id}")
    ResponseEntity<UserEntity[]> getSubscriptionsById(@PathVariable(name = "id") String id) {
        return restTemplate.getForEntity("http://localhost:8080/api/usersubscriptions/" +
                "getusersubscriptionsbyid/" + id, UserEntity[].class);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("getsubscriptionsdata/{userid}/{curruserid}")
    ResponseEntity<SubscriptionData> getSubscriptionData(@PathVariable(name = "userid") String userId,
                                                         @PathVariable(name = "curruserid") String currUserId) {
        return restTemplate.getForEntity("http://localhost:8080/api/usersubscriptions/" +
                "getsubscriptionsdata/" + userId + "/" + currUserId, SubscriptionData.class);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("subscribe")
    SubscriptionData addSubscriptions(@RequestBody UserSubscription userSubscription) {
        return restTemplate.postForEntity("http://localhost:8080/api/usersubscriptions/" +
                "subscribe/", userSubscription, SubscriptionData.class).getBody();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("unsubscribe")
    public SubscriptionData unsubscribe(@RequestBody UserSubscription userSubscription) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserSubscription> httpEntityData = new HttpEntity<>(userSubscription, headers);
        return restTemplate.exchange("http://localhost:8080/api/usersubscriptions/unsubscribe",
                HttpMethod.DELETE, httpEntityData, SubscriptionData.class).getBody();
    }
}
