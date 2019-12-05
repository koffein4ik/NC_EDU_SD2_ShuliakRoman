import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Post } from '../../models/post';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) { }

  private userUrl = 'http://localhost:8081/api/posts/getallposts';
  private hashtagUrl = 'http://localhost:8081/api/hashtags/getpostsbyhashtag/';
  private nicknameUrl = 'http://localhost:8081/api/posts/getpostsbynickname/';
  private postSubscriptionsUrl = 'http://localhost:8081/api/posts/getpostsfromsubscriptions/';
  private deletePostUrl = 'http://localhost:8081/api/posts/deletepost/';

  getPosts() {
    return this.http.get<Post[]>(this.userUrl);
  }

  getPostsByHashtagText(text: string) {
    return this.http.get<Post[]>(this.hashtagUrl + text);
  }

  getPostsByNickname(nickname: string) {
    return this.http.get<Post[]>(this.nicknameUrl + nickname);
  }

  deletePostById(postId: number, userId: number) {
     return this.http.delete<Post[]>(this.deletePostUrl + postId + "/" + userId);
  }

  //Сделать везде ник или id
  getPostsFromSubscriptions(id: number) {
    return this.http.get<Post[]>(this.postSubscriptionsUrl + id);
  }

}
