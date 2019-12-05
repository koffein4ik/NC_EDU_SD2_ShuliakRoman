import { Component, OnInit } from '@angular/core';
import { ReportService } from '../../services/report.service';
import { ReportView } from 'src/app/models/reportView';
import { PostService } from '../../services/postservice/post.service';
import { Post } from '../../models/post';
import { throwMatDialogContentAlreadyAttachedError } from '@angular/material';

@Component({
  selector: 'app-reportspage',
  templateUrl: './reportspage.component.html',
  styleUrls: ['./reportspage.component.css']
})
export class ReportspageComponent implements OnInit {

  reports: ReportView[] = [];
  displayCheckedReports = false;

  constructor(private reportService: ReportService, private postService: PostService) { }

  ngOnInit() {
    if (this.displayCheckedReports) {
      this.reportService.getCheckedReports().subscribe(value => {
        console.log(value);
        this.reports = value;
      })
    }
    else {
      this.reportService.getReports().subscribe(value => {
        console.log(value);
        this.reports = value;
      })
    }
  }

  blockUser(id: number) {
    this.reportService.blockUser(id).subscribe(() => {
      this.ngOnInit();
    })
  }

  unblockUser(id: number) {
    this.reportService.unblockUser(id).subscribe(() => {
      this.ngOnInit();
    })
  }

  deletePost(post: Post) {
    this.postService.deletePostById(post.postId, post.user.id).subscribe(() => {
      this.ngOnInit();
    })
  }

  markAsChecked(id: number) {
    this.reportService.markAsChecked(id).subscribe(value => {
      this.reports = value;
    })
  }

  displayChecked() {
    this.displayCheckedReports = !this.displayCheckedReports;
    console.log(this.displayCheckedReports);
    this.ngOnInit();
  }

}
