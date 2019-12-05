import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Post } from '../../../../models/post';
import { CommentService } from '../../../../services/commentservice/comment.service';
import { IgxCarouselModule } from 'igniteui-angular';
import { MatCarouselSlide, MatCarouselSlideComponent } from '@ngmodule/material-carousel';
import { MatInputModule } from '@angular/material/input';
import { ReportdialogComponent } from '../../../components/reportdialog/reportdialog.component';
import { MatDialog } from '@angular/material';
import { Report } from '../../../../models/report';
import { PostService } from '../../../../services/postservice/post.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  @Input() post: Post;
  @Output() onDelete = new EventEmitter<any>();

  currUserId: number;

  constructor(public dialog: MatDialog, private postService: PostService) { }

  private avatarUrl = "http://localhost:8081/api/users/getuseravatar/";

  replaceHashtagsWithLinks() {
    this.post.description = "<p>" + this.post.description + "</p>";
    this.post.hashtags.forEach(tag => {
      this.replaceHashTagByLink(tag.text);
    });
  }

  public replaceHashTagByLink(hashtag: string): void {
    const link = '<a href="/hashtag/' + hashtag + '">#' + hashtag + '</a>';
    this.post.description = this.post.description.replace("#" + hashtag, link)
  }

  reportClick() {
    if (localStorage.getItem('userid') !== undefined) {
      console.log("report");
      let report: Report = new Report();
      report.postId = this.post.postId;
      report.senderUserId = this.currUserId;
      report.reportedUserId = this.post.user.id;
      const dialogRef = this.dialog.open(ReportdialogComponent, {
        width: '40%',
        data: report
      });

      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
      });
    } else {
      window.confirm("You have to be authorized to send reports");
    }
  }

  deleteClick() {
    this.postService.deletePostById(this.post.postId, this.post.user.id).subscribe(() => {
      this.onDelete.emit(null);
    });
  }


  ngOnInit() {
    this.replaceHashtagsWithLinks();
    if (localStorage.getItem('userId') !== undefined) {
      this.currUserId = +localStorage.getItem('userid');
    }
    this.avatarUrl += this.post.user.id;
    for(var i = 0; i < this.post.photoURIs.length; i++) {
      this.post.photoURIs[i] = 'http://localhost:8081/api/posts/images/' + this.post.user.id + '/' + this.post.postId + '/' +
        this.post.photoURIs[i];
    }
  }
}
