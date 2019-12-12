import { Component, OnInit, HostListener } from '@angular/core';
import { PostService } from '../../services/postservice/post.service';
import { Post } from '../../models/post';
import { PostComponent } from './components/post/post.component';
import { IgxCarouselModule } from 'igniteui-angular';

@Component({
  selector: 'app-feedpage',
  templateUrl: './feedpage.component.html',
  styleUrls: ['./feedpage.component.css']
})
export class FeedpageComponent implements OnInit {

  constructor(private postService: PostService) { }

  private posts: Post[];
  loadingMore = false;
  allPostsLoaded = false;
  page = 0;

  delete() {
    this.ngOnInit();
  }

  ngOnInit() {
    this.page = 0;
    this.allPostsLoaded = false;
    this.postService.getPosts(this.page).subscribe(value => {
        this.posts = value;
        this.page++;
        console.log("inited");
    });
  }

  @HostListener('window:scroll', ['$event'])
  onWindowScroll(event) {
    let pos = (document.documentElement.scrollTop || document.body.scrollTop) + document.documentElement.offsetHeight;
    let max = document.documentElement.scrollHeight;
    if (!this.loadingMore  && !this.allPostsLoaded) {
      if (pos > ((max) * 0.9)) {
        console.log("loading more");
        this.loadingMore = true;
        this.postService.getPosts(this.page).subscribe(value => {
          if (value.length == 0) {
            this.allPostsLoaded = true;
          }
          else {
            this.page++;
          }
          setTimeout(function() { }, 1000);
          this.posts = this.posts.concat(value);
          this.loadingMore = false;
        })
      }
    }
  }

}
