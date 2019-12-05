package com.nc.backend.model;

public class SubscriptionData {
    private int subscribers;
    private int subscriptions;
    private boolean isCurrUserSubscribed;

    public int getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(int subscribers) {
        this.subscribers = subscribers;
    }

    public int getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(int subscriptions) {
        this.subscriptions = subscriptions;
    }

    public boolean isCurrUserSubscribed() {
        return isCurrUserSubscribed;
    }

    public void setCurrUserSubscribed(boolean currUserSubscribed) {
        isCurrUserSubscribed = currUserSubscribed;
    }

}
