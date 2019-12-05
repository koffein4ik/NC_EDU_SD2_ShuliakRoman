package com.nc.backend.model;

public class UserSubscription {
    private int subscriberId;
    private int subscribedToId;

    public int getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(int subscriberId) {
        this.subscriberId = subscriberId;
    }

    public int getSubscribedToId() {
        return subscribedToId;
    }

    public void setSubscribedToId(int subscribedToId) {
        this.subscribedToId = subscribedToId;
    }
}
