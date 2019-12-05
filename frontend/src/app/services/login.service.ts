import { Injectable, Output, EventEmitter } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Subject, ReplaySubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  userLoggedIn: Subject<void> = new ReplaySubject(1);

  backUrl = 'http://localhost:8081/api/users/authorization';

  displayLogin() {
    this.userLoggedIn.next();
  }

  verifyLogin(userLoginData) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    return this.http.post(this.backUrl, JSON.stringify(userLoginData), {headers});
  }
}
