import { Component, OnInit, HostListener, Inject } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';
import { ActivatedRoute } from '@angular/router';
import { PostService } from '../../services/postservice/post.service';
import { Post } from '../../models/post';
import { StorageUserModel } from '../../models/storageUserModel';
import { SubscriptionsService } from '../../services/subscriptions.service';
import { StorageService } from '../../services/storage.service';
import { SubscriptionData } from '../../models/subscriptiondata';
import { I18nSelectPipe } from '@angular/common';

@Component({
  selector: 'app-userpage',
  templateUrl: './userpage.component.html',
  styleUrls: ['./userpage.component.css']
})

export class UserpageComponent implements OnInit {

  user: User;
  currUser: StorageUserModel;
  userLoaded = false;
  page = 0;
  userAvatar: string;
  posts: Post[];
  subscriptionData: SubscriptionData;
  loadingMore = false;
  allPostsLoaded = false;

  constructor(private userService: UserService, private activatedRoute: ActivatedRoute, private postService: PostService,
    private subscriptionService: SubscriptionsService, private storageService: StorageService) { }

    ngOnInit() {
      this.allPostsLoaded = false;
      this.currUser = this.storageService.getCurrentUser();
      this.activatedRoute.params.subscribe((params) => {
        if (params.nickname !== undefined) {
          this.userService.getUserByNickname(params.nickname).subscribe(value => {
            this.user = value;
            this.getSubscriptions();
            this.userAvatar = 'http://localhost:8081/api/users/getuseravatar/' + this.user.id;
            this.userLoaded = true;
          })
          this.page = 0;
          this.postService.getPostsByNickname(params.nickname, this.page).subscribe(value => {
            this.posts = value;
            this.page++;
          });         
        }
    })
  }

  @HostListener('window:scroll', ['$event'])
  onWindowScroll(event) {
    let pos = (document.documentElement.scrollTop || document.body.scrollTop) + document.documentElement.offsetHeight;
    let max = document.documentElement.scrollHeight;
    if (!this.loadingMore  && !this.allPostsLoaded && this.user) {
      if (pos > ((max) * 0.9)) {
        console.log("loading more");
        this.loadingMore = true;
        this.postService.getPostsByNickname(this.user.nickname, this.page).subscribe(value => {
          if (value.length == 0) {
            this.allPostsLoaded = true;
          }
          else {
            this.page++;
          }
          this.posts = this.posts.concat(value);
          this.loadingMore = false;
        })
      }
    }
  }

  delete() {
    this.postService.getPostsByNickname(this.user.nickname, 0).subscribe(value => {
      this.posts = value;
    });
  }

  getSubscriptions() {
    if(this.currUser) {
      this.subscriptionService.getUserSubscriptions(this.user.id, this.currUser.id).subscribe(value => {
        this.subscriptionData = value;
      })
    }
  }

  subscribe() {
    if (this.currUser) {
      this.subscriptionService.subscribe(this.currUser.id, this.user.id).subscribe(value => {
        this.subscriptionData = value;
      })
    }
  }

  unsubscribe() {
    if (this.currUser) {
      this.subscriptionService.unsubscribe(this.currUser.id, this.user.id).subscribe(value => {
        this.subscriptionData = value;
      })
    }
  }

}
