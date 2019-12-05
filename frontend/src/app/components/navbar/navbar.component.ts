import { Component, OnInit, HostListener } from '@angular/core';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private loginService: LoginService) { }

  currUser;
  currUserRole = "";
  currUserUrl = '/user';

  displayUserNickname() {
    this.currUser = localStorage.getItem('username');
    this.currUserRole = localStorage.getItem('role');
    this.currUserUrl += "/" + this.currUser;
  }

  ngOnInit() {
    this.displayUserNickname();
    this.loginService.userLoggedIn.subscribe(() => {
      this.displayUserNickname();
    });
  }

}
