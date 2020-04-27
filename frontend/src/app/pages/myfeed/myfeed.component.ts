import { Component, OnInit, HostListener } from '@angular/core';
import { PostService } from '../../services/postservice/post.service';
import { StorageUserModel } from '../../models/storageUserModel';
import { StorageService } from '../../services/storage.service';
import { Post } from '../../models/post';

@Component({
  selector: 'app-myfeed',
  templateUrl: './myfeed.component.html',
  styleUrls: ['./myfeed.component.css']
})
export class MyfeedComponent implements OnInit {

  constructor(private postService: PostService, private storageService: StorageService) { }

  posts: Post[] = [];
  currUser: StorageUserModel;
  loadingMore = false;
  allPostsLoaded = false;
  page = 0;

  delete() {
    this.ngOnInit();
  }
  
  ngOnInit() {
    this.allPostsLoaded = false;
    this.currUser = this.storageService.getCurrentUser();
    if (this.currUser) {
      this.page = 0;
      this.postService.getPostsFromSubscriptions(this.currUser.id, this.page).subscribe(value => {
        this.page++;
        this.posts = value
      })
    }
  }

  @HostListener('window:scroll', ['$event'])
  onWindowScroll(event) {
    let pos = (document.documentElement.scrollTop || document.body.scrollTop) + document.documentElement.offsetHeight;
    let max = document.documentElement.scrollHeight;
    if (!this.loadingMore  && !this.allPostsLoaded) {
      if (pos > ((max) * 0.9)) {
        this.loadingMore = true;
        this.postService.getPostsFromSubscriptions(this.currUser.id, this.page).subscribe(value => {
          if (value.length == 0) {
            this.allPostsLoaded = true;
          }
          else {
            this.page++;
          }
          // setTimeout(function() { }, 1000);
          this.posts = this.posts.concat(value);
          this.loadingMore = false;
        })
      }
    }
  }

}