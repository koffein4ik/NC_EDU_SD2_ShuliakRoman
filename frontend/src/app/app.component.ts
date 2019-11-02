import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'frontend';

  // constructor(private hhtp: HttpClient) { }
  
  // ngOnInit(){
  //   console.log("init");
  //   this.hhtp.get('http://localhost:8081/getusers').subscribe((value) => console.log(value));
  // }

  constructor() { }

  ngOnInit() {
    //this.getUsersService.get('http://localhost:8081/getusers').subscribe((value) =>
    //  console.log(value));
  }
}
