import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';
import { TouchSequence } from 'selenium-webdriver';
import { StorageService } from '../../services/storage.service';
import { StorageUserModel } from '../../models/storageUserModel';

@Component({
  selector: 'app-edituserinfopage',
  templateUrl: './edituserinfopage.component.html',
  styleUrls: ['./edituserinfopage.component.css']
})
export class EdituserinfopageComponent implements OnInit {

  constructor(private userService: UserService, private storageService: StorageService) { }

  user: User;
  storageUserModel: StorageUserModel;
  updated = false;
  avatar: File;
  
  ngOnInit() {
    this.storageUserModel = this.storageService.getCurrentUser();
    if (this.storageUserModel) {
      this.userService.getUserByNickname(this.storageUserModel.username).subscribe(value => {
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
      this.userService.uploadAvatar(this.avatar, this.user.id).subscribe(value => {
        this.updated = true;
      });
    } else {
      window.alert("You have to pick file to upload before submitting")
    }
  }

  handleFileInput(file: File) {
    console.log(file);
    this.avatar = file;
  }
}
