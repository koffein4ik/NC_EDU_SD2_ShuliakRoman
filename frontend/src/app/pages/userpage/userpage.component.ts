import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';
import { ActivatedRoute } from '@angular/router';
import { PostService } from '../../services/postservice/post.service';
import { Post } from '../../models/post';
import { SubscriptionsService } from '../../services/subscriptions.service';
import { SubscriptionData } from '../../models/subscriptiondata';

@Component({
  selector: 'app-userpage',
  templateUrl: './userpage.component.html',
  styleUrls: ['./userpage.component.css']
})

export class UserpageComponent implements OnInit {

  user: User;
  currUser = 0;
  userAvatar: string;
  posts: Post[];
  subscriptionData: SubscriptionData;

  constructor(private userService: UserService, private activatedRoute: ActivatedRoute, private postService: PostService,
    private subscriptionService: SubscriptionsService) { }

    ngOnInit() {
      this.activatedRoute.params.subscribe((params) => {
        if (params.nickname !== undefined) {
          if(localStorage.getItem('userid') !== undefined) {
            this.currUser = +localStorage.getItem('userid');          
          }

          this.userService.getUserByNickname(params.nickname).subscribe(value => {
            this.user = value;
            this.getSubscriptions();
            this.userAvatar = 'http://localhost:8081/api/users/getuseravatar/' + this.user.id;
          })

          this.postService.getPostsByNickname(params.nickname).subscribe(value => {
            this.posts = value;
          });         
        }
    })
  }

  delete() {
    this.postService.getPostsByNickname(this.user.nickname).subscribe(value => {
      this.posts = value;
    });
  }

  getSubscriptions() {
    this.subscriptionService.getUserSubscriptions(this.user.id, this.currUser).subscribe(value => {
      this.subscriptionData = value;
    })
  }

  subscribe() {
    this.subscriptionService.subscribe(this.currUser, this.user.id).subscribe(value => {
      this.subscriptionData = value;
    })
  }

  unsubscribe() {
    this.subscriptionService.unsubscribe(this.currUser, this.user.id).subscribe(value => {
      this.subscriptionData = value;
    })
  }

}
