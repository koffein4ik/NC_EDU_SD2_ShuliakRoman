import { Component, OnInit, HostListener } from '@angular/core';
import { LoginService } from '../../services/login.service';
import { StorageService } from '../../services/storage.service';
import { StorageUserModel } from '../../models/storageUserModel';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private loginService: LoginService, private storageService: StorageService) { }

  currUser: StorageUserModel;
  currUserRole = "";
  currUserUrl = '/user';

  displayUserNickname() {
    this.currUser = this.storageService.getCurrentUser();
    if (this.currUser) {
      this.currUserUrl += "/" + this.currUser.username;
    }
  }

  ngOnInit() {
    this.displayUserNickname();
    this.loginService.userLoggedIn.subscribe(() => {
      this.displayUserNickname();
    });
  }

  logOut() {
    this.storageService.setCurrentUser(null);
    this.storageService.clearToken();
    this.displayUserNickname();
    window.location.reload();
  }

}
