import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';
import { TouchSequence } from 'selenium-webdriver';

@Component({
  selector: 'app-edituserinfopage',
  templateUrl: './edituserinfopage.component.html',
  styleUrls: ['./edituserinfopage.component.css']
})
export class EdituserinfopageComponent implements OnInit {

  constructor(private userService: UserService) { }

  user: User;
  updated = false;
  avatar: File;
  
  ngOnInit() {
    if (localStorage.getItem('username') !== null) {
      this.userService.getUserByNickname(localStorage.getItem('username')).subscribe(value => {
        this.user = value;
      })
    }
  }

  onClickSubmit() {
    this.userService.updateUserInfo(this.user).subscribe(value => {
      this.user = value;
      this.updated = true;
    })
  }

  onAvatarClickSubmit() {
    if (this.avatar) {
      this.userService.uploadAvatar(this.avatar, this.user.id).subscribe();
    } else {
      window.alert("You have to pick file to upload before submitting")
    }
  }

  handleFileInput(file: File) {
    console.log(file);
    this.avatar = file;
  }
}
