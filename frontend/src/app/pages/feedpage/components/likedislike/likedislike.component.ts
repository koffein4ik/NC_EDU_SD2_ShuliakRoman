import { Component, OnInit, Input } from '@angular/core';
import { LikesdilikesService } from '../../../../services/likesdilikes.service';
import { LikeDislike } from '../../../../models/likedislike';

@Component({
  selector: 'app-likedislike',
  templateUrl: './likedislike.component.html',
  styleUrls: ['./likedislike.component.css']
})
export class LikedislikeComponent implements OnInit {

  constructor(private likeDislikeService: LikesdilikesService) { }

  private likeDislike: LikeDislike;

  private likeStyle = "/../assets/like.svg";
  private dislikeStyle = "/../assets/dislike.svg";

  likeClick() {
    if (localStorage.getItem('userid') !== null) {
      if(this.likeStyle == "/../assets/like.svg") {
        this.likeDislikeService.setLikeDislike(this.postId, +localStorage.getItem('userid'), true).subscribe(
          value => this.likeDislike = value
        );
        this.likeStyle = "/../assets/paintedlike.svg";
        this.dislikeStyle = "/../assets/dislike.svg";
      }
      else {
        this.likeStyle = "/../assets/like.svg";
        if (localStorage.getItem('userid') !== null) {
          this.likeDislikeService.removeLikeDislike(this.postId, +localStorage.getItem('userid')).subscribe(value => {
            this.likeDislike = value;
          })
        }
      }
    }
    else {
      console.log("You have to authorize");
    }
  }

  dislikeClick() {
    if (localStorage.getItem('userid') !== null) {
      if(this.dislikeStyle == "/../assets/dislike.svg") {
        this.likeDislikeService.setLikeDislike(this.postId, +localStorage.getItem('userid'), false).subscribe(
          value =>  {
            this.likeDislike = value;
          }
        );
        this.dislikeStyle = "/../assets/painteddislike.svg";
        this.likeStyle = "/../assets/like.svg";
      }
      else {
        this.dislikeStyle = "/../assets/dislike.svg";
        if (localStorage.getItem('userid') !== null) {
          this.likeDislikeService.removeLikeDislike(this.postId, +localStorage.getItem('userid')).subscribe(value => {
            this.likeDislike = value;
          })
        }
      }
    }
  }

  @Input() postId: number;

  ngOnInit() {
    this.likeDislikeService.getLikesDislikesCountByPostId(this.postId).subscribe(value => this.likeDislike = value);
    if (localStorage.getItem('userid') !== null) {
      this.likeDislikeService.getUserReactionByPostId(this.postId, +localStorage.getItem('userid')).subscribe(value => {
        if (value !== null) {
          if (value.type == 1) {
            this.likeStyle = "/../assets/paintedlike.svg";
          }
          if (value.type == 0) {
            this.dislikeStyle = "/../assets/painteddislike.svg";
          }
        }
      })
    }
  }

}
