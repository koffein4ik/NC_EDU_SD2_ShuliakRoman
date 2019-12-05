package com.nc.backend.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_subscriptions", schema = "photosquare", catalog = "")
public class UserSubscriptionsEntity {

    private int userSubscriptionId;
    private UserEntity subscriber;
    private UserEntity subscribedTo;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public UserEntity getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(UserEntity subscriber) {
        this.subscriber = subscriber;
    }

    @ManyToOne
    @JoinColumn(name = "subscribed_to_user_id")
    public UserEntity getSubscribedTo() {
        return subscribedTo;
    }

    public void setSubscribedTo(UserEntity subscribedTo) {
        this.subscribedTo = subscribedTo;
    }

    @Id
    @Column(name = "user_subscription_id")
    public int getUserSubscriptionId() {
        return userSubscriptionId;
    }

    public void setUserSubscriptionId(int userSubscriptionId) {
        this.userSubscriptionId = userSubscriptionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSubscriptionsEntity that = (UserSubscriptionsEntity) o;
        return userSubscriptionId == that.userSubscriptionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userSubscriptionId);
    }
}
