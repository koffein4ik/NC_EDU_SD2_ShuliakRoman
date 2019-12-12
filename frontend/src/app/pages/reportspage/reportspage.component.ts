import { Component, OnInit, HostListener } from '@angular/core';
import { ReportService } from '../../services/report.service';
import { ReportView } from 'src/app/models/reportView';
import { PostService } from '../../services/postservice/post.service';
import { Post } from '../../models/post';
import { throwMatDialogContentAlreadyAttachedError } from '@angular/material';
import { StorageService } from '../../services/storage.service';
import { StorageUserModel } from '../../models/storageUserModel';

@Component({
  selector: 'app-reportspage',
  templateUrl: './reportspage.component.html',
  styleUrls: ['./reportspage.component.css']
})
export class ReportspageComponent implements OnInit {

  reports: ReportView[] = [];
  currUser: StorageUserModel;
  displayCheckedReports = false;
  loadingMore = false;
  allPostsLoaded = false;
  page = 0;

  constructor(private reportService: ReportService, private postService: PostService,
    private storageService: StorageService) { }

  ngOnInit() {
    this.allPostsLoaded = false;
    this.currUser = this.storageService.getCurrentUser();
    this.page = 0;
    if (this.currUser.role === 'Admin') {
      if (this.displayCheckedReports) {
        this.reportService.getCheckedReports(this.page).subscribe(value => {
          console.log(value);
          this.reports = value;
          this.page++;
        })
      }
      else {
        this.reportService.getReports(this.page).subscribe(value => {
          console.log(value);
          this.reports = value;
          this.page++;
        })
      }
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
      this.page = 1;
    })
  }

  displayChecked() {
    this.displayCheckedReports = !this.displayCheckedReports;
    console.log(this.displayCheckedReports);
    this.ngOnInit();
  }

  @HostListener('window:scroll', ['$event'])
  onWindowScroll(event) {
    let pos = (document.documentElement.scrollTop || document.body.scrollTop) + document.documentElement.offsetHeight;
    let max = document.documentElement.scrollHeight;
    if (!this.loadingMore  && !this.allPostsLoaded) {
      if (pos > ((max) * 0.9)) {
        console.log("loading more");
        this.loadingMore = true;
        if (this.displayCheckedReports) {
          this.reportService.getCheckedReports(this.page).subscribe(value => {
            if (value.length == 0) {
              this.allPostsLoaded = true;
            }
            else {
              this.page++;
            }
            this.reports = this.reports.concat(value);
            this.loadingMore = false;
          })
        }
        else {
          this.reportService.getReports(this.page).subscribe(value => {
            if (value.length == 0) {
              this.allPostsLoaded = true;
            }
            else {
              this.page++;
            }
            this.reports = this.reports.concat(value);
            this.loadingMore = false;
          })
        }
      }
    }
  }
}
