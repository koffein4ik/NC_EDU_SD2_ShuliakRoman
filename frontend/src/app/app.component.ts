import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { StorageService } from './services/storage.service';
import { UserService } from './services/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  constructor(private http: HttpClient, private storageService: StorageService, private userService: UserService) { }
  
  ngOnInit(){
    if (this.storageService.getCurrentUser()) {
      this.userService.checkToken().subscribe((value) => {
      }, ((error) => {
        this.storageService.setCurrentUser(null);
        this.storageService.clearToken();
        window.location.reload();
      }));
    }
  }


}
