import { Component, OnInit, Input } from '@angular/core';
import { LikesdilikesService } from '../../../../services/likesdilikes.service';
import { LikeDislike } from '../../../../models/likedislike';
import { StorageUserModel } from '../../../../models/storageUserModel';
import { StorageService } from '../../../../services/storage.service';

@Component({
  selector: 'app-likedislike',
  templateUrl: './likedislike.component.html',
  styleUrls: ['./likedislike.component.css']
})
export class LikedislikeComponent implements OnInit {

  constructor(private likeDislikeService: LikesdilikesService, private storageService: StorageService) { }

  private likeDislike: LikeDislike;
  private currUser: StorageUserModel;

  private likeStyle = "/../assets/like.svg";
  private dislikeStyle = "/../assets/dislike.svg";

  likeClick() {
    if (this.currUser !== null) {
      if(this.likeStyle == "/../assets/like.svg") {
        this.likeDislikeService.setLikeDislike(this.postId, this.currUser.id, true).subscribe(
          value => this.likeDislike = value
        );
        this.likeStyle = "/../assets/paintedlike.svg";
        this.dislikeStyle = "/../assets/dislike.svg";
      }
      else {
        this.likeStyle = "/../assets/like.svg";
        if (this.currUser !== null) {
          this.likeDislikeService.removeLikeDislike(this.postId, this.currUser.id).subscribe(value => {
            this.likeDislike = value;
          })
        }
      }
    }
    else {
      alert('You have to be authorized to put likes');
    }
  }

  dislikeClick() {
    if (this.currUser !== null) {
      if(this.dislikeStyle == "/../assets/dislike.svg") {
        this.likeDislikeService.setLikeDislike(this.postId, this.currUser.id, false).subscribe(
          value =>  {
            this.likeDislike = value;
          }
        );
        this.dislikeStyle = "/../assets/painteddislike.svg";
        this.likeStyle = "/../assets/like.svg";
      }
      else {
        this.dislikeStyle = "/../assets/dislike.svg";
        if (this.currUser !== null) {
          this.likeDislikeService.removeLikeDislike(this.postId, this.currUser.id).subscribe(value => {
            this.likeDislike = value;
          })
        }
      }
    } else {
      alert('You have to be authorized to put dislikes');
    }
  }

  @Input() postId: number;

  ngOnInit() {
    this.likeDislikeService.getLikesDislikesCountByPostId(this.postId).subscribe(value => this.likeDislike = value);
    this.currUser = this.storageService.getCurrentUser();
    if (this.currUser !== null) {
      this.likeDislikeService.getUserReactionByPostId(this.postId, this.currUser.id).subscribe(value => {
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
