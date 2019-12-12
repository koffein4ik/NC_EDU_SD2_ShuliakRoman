import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { User } from '../models/user';
import { SubscriptionData } from '../models/subscriptiondata';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionsService {

  constructor(private http: HttpClient) {}

  // getUserSubscriptionsDataUrl = "http://localhost:8081/api/subscriptions/getsubscriptionsdata/";
  // subscribeUrl = "http://localhost:8081/api/subscriptions/subscribe/";
  // unsubscribeUrl = "http://localhost:8081/api/subscriptions/unsubscribe/";
  
  getUserSubscriptions(id: number, currUserId: number) {
    return this.http.get<SubscriptionData>("/api/subscriptions/getsubscriptionsdata/" + id + "/" + currUserId);
  }

  subscribe(subscriberId: number, subscribeTo: number) {
    var data = {
      subscriberId: subscriberId,
      subscribedToId: subscribeTo
    }
    return this.http.post<SubscriptionData>("/api/subscriptions/subscribe/", data);
  }

  unsubscribe(subscriberId: number, subscribeTo: number) {
    const options = {
      headers: new HttpHeaders({
       'Content-Type': 'application/json',
      }),
      body: {
        subscriberId: subscriberId,
        subscribedToId: subscribeTo
      },
    };
     return this.http.delete<SubscriptionData>("/api/subscriptions/unsubscribe/", options);
  }
  
}
