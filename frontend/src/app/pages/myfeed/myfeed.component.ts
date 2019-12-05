import { Component, OnInit } from '@angular/core';
import { PostService } from '../../services/postservice/post.service';
import { Post } from '../../models/post';
import { User } from '../../models/user';
import { SubscriptionsService } from '../../services/subscriptions.service';

@Component({
  selector: 'app-myfeed',
  templateUrl: './myfeed.component.html',
  styleUrls: ['./myfeed.component.css']
})
export class MyfeedComponent implements OnInit {

  constructor(private postService: PostService) { }

  posts: Post[] = [];

  delete() {
    this.ngOnInit();
  }
  
  ngOnInit() {
    if (localStorage.getItem('userid')) {
      this.postService.getPostsFromSubscriptions(+localStorage.getItem('userid')).subscribe(value => {
        this.posts = value
      })
    }
  }

}