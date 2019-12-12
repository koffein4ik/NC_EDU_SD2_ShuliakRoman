import { Component, OnInit, HostListener } from '@angular/core';
import { PostService } from '../../services/postservice/post.service';
import { Post } from '../../models/post';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-hashtagpage',
  templateUrl: './hashtagpage.component.html',
  styleUrls: ['./hashtagpage.component.css']
})
export class HashtagpageComponent implements OnInit {

  constructor(private postService: PostService, private activatedRoute: ActivatedRoute,
    private router: Router) { }

  private posts: Post[] = [];
  initialHashtagText = "";
  inputValue = "";
  hashtagsLoaded = false;
  loadingMore = false;
  allPostsLoaded = false;
  page = 0;

  ngOnInit() {
    this.allPostsLoaded = false;
    this.activatedRoute.params.subscribe((params) => {
      if (params.hashtag !== undefined) {
        this.initialHashtagText = params.hashtag;
        this.inputValue = this.initialHashtagText;
        this.page = 0;
        this.postService.getPostsByHashtagText(this.initialHashtagText, this.page).subscribe(value => {
          this.posts = value;
          this.page++;
          this.hashtagsLoaded = true;
       })
      }
    })
  }

  onClickSubmit(data) {
    this.router.navigate(["/hashtag/" + data.hashtag]);
  }

  @HostListener('window:scroll', ['$event'])
  onWindowScroll(event) {
    let pos = (document.documentElement.scrollTop || document.body.scrollTop) + document.documentElement.offsetHeight;
    let max = document.documentElement.scrollHeight;
    if (!this.loadingMore  && !this.allPostsLoaded) {
      if (pos > ((max) * 0.9)) {
        console.log("loading more");
        this.loadingMore = true;
        this.postService.getPostsByHashtagText(this.initialHashtagText, this.page).subscribe(value => {
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
