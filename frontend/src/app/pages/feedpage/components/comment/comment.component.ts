import { Component, OnInit, Input } from '@angular/core';
import { CommentService } from '../../../../services/commentservice/comment.service';
import { StorageService } from '../../../../services/storage.service';
import { Comment } from '../../../../models/comment';
import { StorageUserModel } from '../../../../models/storageUserModel';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {

  constructor(private commentService: CommentService, private storageService: StorageService) { }

  comments: Comment[] = [];
  currUser: StorageUserModel;
  commentText = "";
  showComments = false;

  @Input() postId: number;

  ngOnInit() {
    this.commentService.getCommentsByPostId(this.postId).subscribe(value => this.comments = value);
    this.currUser = this.storageService.getCurrentUser();
  }

  onClickSubmit(data) {
    if (this.currUser !== null) {
      this.commentService.submitComment(this.postId, this.currUser.id, data.commentText).subscribe((value) => this.comments = value);
      this.commentText = "";
    } else {
      alert('You have to be authorized to leave comments');
    }
  }

  showCommentsClick() {
    this.showComments = true;
  }

  hideCommentsClick() {
    this.showComments = false;
  }

}
