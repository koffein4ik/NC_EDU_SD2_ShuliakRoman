import { Component, OnInit } from '@angular/core';
import { MatCarouselModule } from '@ngmodule/material-carousel';
import { LoginService } from '../../services/login.service';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';
import { StorageService } from '../../services/storage.service';
import { Router } from '@angular/router';
import { LoginData } from 'src/app/models/loginData';
import { StorageUserModel } from 'src/app/models/storageUserModel';

@Component({
  selector: 'app-loginpage',
  templateUrl: './loginpage.component.html',
  styleUrls: ['./loginpage.component.css']
})
export class LoginpageComponent implements OnInit {

  constructor(private loginService: LoginService, private userService: UserService, 
    private storageService: StorageService, private router: Router) { }

  loginError = false;
  currUser: User;

  ngOnInit() {
  }

  onClickSubmit(formData: LoginData) {
    this.loginService.getToken(formData).subscribe((value) => {
      if (!value) {
        this.loginError = true;
      }
      else {
        this.storageService.setToken(value.token);
        this.userService.getUserByNickname(formData.login).subscribe(value => {
          let storageUserModel = new StorageUserModel(value.id, value.nickname, value.role);
          this.storageService.setCurrentUser(storageUserModel);
          this.loginService.displayLogin();
          this.router.navigate(['/feed']);
        })
      }
    }, (error) => {
      if (error.status === 401) {
        this.loginError = true;
      } else {
        alert(error.message);
      }
    });
  }

}
