import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { IgxDatePickerComponent } from 'igniteui-angular';
import { ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserService } from '../../../../services/user.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(private location: Location, private userService: UserService) { }

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
      formData.lastname, formData.psw).subscribe(response => console.log(response));
  }

}
