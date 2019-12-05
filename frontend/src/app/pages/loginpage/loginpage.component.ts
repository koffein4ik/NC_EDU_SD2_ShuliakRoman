import { Component, OnInit } from '@angular/core';
import { MatCarouselModule } from '@ngmodule/material-carousel';
import { LoginService } from '../../services/login.service';
import { User } from '../../models/user';

@Component({
  selector: 'app-loginpage',
  templateUrl: './loginpage.component.html',
  styleUrls: ['./loginpage.component.css']
})
export class LoginpageComponent implements OnInit {

  constructor(private loginService: LoginService) { }

  loginError = false;
  currUser: User;

  ngOnInit() {
  }

  onClickSubmit(formData) {
    console.log(formData);
    this.loginService.verifyLogin(formData).subscribe(value => {
      if (value === null) {
        console.error("null");
        this.loginError = true;
      }
      else {
        this.currUser = value as User;
        localStorage.setItem("username", this.currUser.nickname);
        localStorage.setItem("userid", "" + this.currUser.id);
        console.log(this.currUser.role);
        if (this.currUser.role == "Admin") {
          localStorage.setItem("role", "admin");
        }
        else if (this.currUser.role == "User") {
          localStorage.setItem("role", "user");
        }
        this.loginService.displayLogin();
      }
    });
  }

}
