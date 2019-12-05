import { Component, OnInit, Input } from '@angular/core';
import { CommentService } from '../../../../services/commentservice/comment.service';
import { Comment } from '../../../../models/comment';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {

  constructor(private commentService: CommentService) { }

  comments: Comment[] = [];
  showComments = false;

  @Input() postId: number;

  ngOnInit() {
    this.commentService.getCommentsByPostId(this.postId).subscribe(value => this.comments = value);
  }

  onClickSubmit(data) {
    if (localStorage.getItem('userid') !== null) {
      this.commentService.submitComment(this.postId, +localStorage.getItem('userid'), data.commentText).subscribe((value) => this.comments = value);
    }
  }

  showCommentsClick() {
    this.showComments = true;
  }

  hideCommentsClick() {
    this.showComments = false;
  }

}
