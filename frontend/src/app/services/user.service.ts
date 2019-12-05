import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  private userUrl = 'http://localhost:8081/api/users/getuserbynickname/';
  private updateUserInfoUrl = 'http://localhost:8081/api/users/updateuserinfo/';
                    
  getUserByNickname(nickname: string): Observable<User> {
    return this.http.get<User>(this.userUrl + nickname);
  }

  regUser(nickname: string, email: string, firstName: string,
    lastName: string, password: string) {
        console.log(firstName);
        let user = {nickname: nickname, name: firstName, surname:lastName, email: email, 
          password: password, role: "User", profileDescription: "", status: "active"} as User;  
        const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
        return this.http.post(this.userUrl + '/regnewuser', JSON.stringify(user), {headers});
  } 

  updateUserInfo(user: User) {
    return this.http.post<User>(this.updateUserInfoUrl, user);
  }

  uploadAvatar(avatar: File, userId: number) {
    console.log(avatar);
    const endpoint = 'http://localhost:8081/api/users/uploadavatar';
    const formData: FormData = new FormData();
    formData.append('fileKey', avatar[0]);
    formData.append('userId', userId.toString());
    return this.http.post(endpoint, formData);
  }
}
