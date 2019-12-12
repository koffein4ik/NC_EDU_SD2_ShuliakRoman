import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { IgxDatePickerComponent } from 'igniteui-angular';
import { ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserService } from '../../../../services/user.service';
import { User } from '../../../../models/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  user: User;
  submitted = false;
  regError = false;
  psw = "";
  repeatPsw = "";

  constructor(private location: Location, private userService: UserService, private router: Router) { }

  goBack(): void {
    this.location.back();
  }
  
  ngOnInit() {
  }

  onCalendarValueSelect(event) {
    console.log(event);
  }

  onClickSubmit(formData): void {
    this.userService.regUser(formData.nickname, formData.email, formData.firstname, 
      formData.lastname, formData.psw).subscribe(response => {
        if (response.id) {
          this.user = response;
          this.submitted = true;
          // this.router.navigate(['/login']);
        } else {
          alert("User with such nickname or e-mail already exists.");
        }
      });
  }

}
