import { Component, OnInit } from '@angular/core';
import { PostService } from '../../services/postservice/post.service';
import { Post } from '../../models/post';
import { PostComponent } from './components/post/post.component';

@Component({
  selector: 'app-feedpage',
  templateUrl: './feedpage.component.html',
  styleUrls: ['./feedpage.component.css']
})
export class FeedpageComponent implements OnInit {

  constructor(private postService: PostService) { }

  private posts: Post[];

  ngOnInit() {
    this.postService.getPosts().subscribe(value => {
        this.posts = value;
    });
  }

}
