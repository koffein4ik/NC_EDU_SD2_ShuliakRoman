import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoginData } from '../models/loginData';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  // private userUrl = 'http://localhost:8081/api/users/getuserbynickname/';
  // private regUrl = 'http://localhost:8081/api/users/regnewuser';
  // private updateUserInfoUrl = 'http://localhost:8081/api/users/updateuserinfo/';
  //private getAllUsersUrl = 'http://localhost:8081/api/users/getallusers/';
                    
  getUserByNickname(nickname: string): Observable<User> {
    return this.http.get<User>("/api/users/getuserbynickname/" + nickname);
  }

  regUser(nickname: string, userEmail: string, firstName: string,
    lastName: string, password: string) {
        let user = {nickname: nickname, email: userEmail, name: firstName, surname:lastName, 
          password: password, role: "User", profileDescription: "", status: "active"} as User;  
        const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
        return this.http.post<User>("/api/users/regnewuser", user, {headers});
  } 

  updateUserInfo(user: User) {
    return this.http.post<User>("/api/users/updateuserinfo/", user);
  }

  getAllUsers(page: number) {
    return this.http.get<User[]>("/api/users/getallusers/" + page);
  }

  public generateToken(login: LoginData): Observable<AuthToken> {
    return this.http.post<AuthToken>("/api/token/generate-token", login);
  }

  uploadAvatar(avatar: File, userId: number) {
    const endpoint = '/api/users/uploadavatar/';
    const formData: FormData = new FormData();
    formData.append('fileKey', avatar[0]);
    formData.append('userId', userId.toString());
    return this.http.post(endpoint, formData);
  }

  checkToken(): Observable<any> {
    return this.http.get('/api/users/checkusertoken');
  }
}

export interface AuthToken {
  readonly token: string;
}
