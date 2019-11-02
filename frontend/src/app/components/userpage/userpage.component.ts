import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';

@Component({
  selector: 'app-userpage',
  templateUrl: './userpage.component.html',
  styleUrls: ['./userpage.component.css']
})
export class UserpageComponent implements OnInit {

  constructor(private route: ActivatedRoute, private userService: UserService, 
    private location: Location) { }

  currUser: User;

  ngOnInit() {
    const nickname = this.route.snapshot.paramMap.get('nickname');
    this.userService.getUserByNickname(nickname).subscribe((response) => this.currUser = response);
    //this.currUser = nickname;
  }

  getUser(): void {
    const nickname = this.route.snapshot.paramMap.get('nickname');
    //this.currUser = nickname;
  }
}
