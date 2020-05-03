import { Component, OnInit, HostListener } from '@angular/core';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';
import { ReportService } from '../../services/report.service';
import { StorageService } from '../../services/storage.service';
import { StorageUserModel } from '../../models/storageUserModel';

@Component({
  selector: 'app-alluserspage',
  templateUrl: './alluserspage.component.html',
  styleUrls: ['./alluserspage.component.css']
})
export class AlluserspageComponent implements OnInit {

  constructor(private userService: UserService, private reportService: ReportService,
    private storageService: StorageService) { }

  users: User[];
  currUser: StorageUserModel;
  displayedColumns: string[] = ['nickname', 'name', 'surname', 'status'];
  loadingMore = false;
  allUsersLoaded = false;
  page = 0;

  ngOnInit() {
    this.page = 0;
    this.allUsersLoaded = false;
    this.currUser = this.storageService.getCurrentUser();
    if (this.currUser.role === 'Admin') {
      this.userService.getAllUsers(this.page).subscribe(value => {
        this.users = value;
        this.page++;
      })
    }
  }

  @HostListener('window:scroll', ['$event'])
  onWindowScroll(event) {
    let pos = (document.documentElement.scrollTop || document.body.scrollTop) + document.documentElement.offsetHeight;
    let max = document.documentElement.scrollHeight;
    if (!this.loadingMore  && !this.allUsersLoaded) {
      if (pos > ((max) * 0.9)) {
        console.log("loading more");
        this.loadingMore = true;
        this.userService.getAllUsers(this.page).subscribe(value => {
          if (value.length == 0) {
            this.allUsersLoaded = true;
          }
          else {
            this.page++;
          }
          this.users = this.users.concat(value);
          this.loadingMore = false;
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

}
