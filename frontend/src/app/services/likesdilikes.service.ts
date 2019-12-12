import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { LikeDislike, UserReaction } from '../models/likedislike';
import { Observable } from 'rxjs';
import { post } from 'selenium-webdriver/http';

@Injectable({
  providedIn: 'root'
})
export class LikesdilikesService {

  constructor(private http: HttpClient) { }

  // private userUrl = 'http://localhost:8081/api/likesdislikes/getlikesdislikescountbypostid/';
  // private putLikeDislikeUrl = 'http://localhost:8081/api/likesdislikes/putlikedislike/';
  // private getUserReactionUrl = 'http://localhost:8081/api/likesdislikes/getuserreaction';
  // private removeUserReactionUrl = 'http://localhost:8081/api/likesdislikes/removeuserreaction';

  getLikesDislikesCountByPostId(postId: number): Observable<LikeDislike> {
    return this.http.get<LikeDislike>('/api/likesdislikes/getlikesdislikescountbypostid/' + postId);
  }

  setLikeDislike(postId: number, userId: number, type: boolean): Observable<LikeDislike> {
    var LikeDislikeData = {
      "postId": postId,
      "userId": userId,
      "type": type
    };
    console.log(LikeDislikeData);
    return this.http.put<LikeDislike>('/api/likesdislikes/putlikedislike', LikeDislikeData);
  }

  removeLikeDislike(postId: number, userId: number): Observable<LikeDislike> {
    let params = new HttpParams();
    params = params.append( "postId", postId.toString());
    const options = {
      headers: new HttpHeaders({
       'Content-Type': 'application/json',
      }),
      body: {
        postId: postId,
        userId: userId
      },
    };
     return this.http.delete<LikeDislike>('/api/likesdislikes/removeuserreaction/', options);
   }

  getUserReactionByPostId(postId: number, userId: number): Observable<UserReaction> {
    var data = {
      "postId": postId,
      "userId": userId
    }
    return this.http.post<UserReaction>('/api/likesdislikes/getuserreaction/', data);
  }
}
