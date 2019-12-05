import { Component, OnInit } from '@angular/core';
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

  ngOnInit() {
    this.activatedRoute.params.subscribe((params) => {
      if (params.hashtag !== undefined) {
        this.initialHashtagText = params.hashtag;
        this.inputValue = this.initialHashtagText;

        this.postService.getPostsByHashtagText(this.initialHashtagText).subscribe(value => {
          this.posts = value;
       })
      }
    })
  }

  onClickSubmit(data) {
    this.router.navigate(["/hashtag/" + data.hashtag]);
  }

}
