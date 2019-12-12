import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Post } from '../../models/post';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) { }

  // private userUrl = 'http://localhost:8081/api/posts/getallposts/';
  // private hashtagUrl = 'http://localhost:8081/api/hashtags/getpostsbyhashtag/';
  // private nicknameUrl = 'http://localhost:8081/api/posts/getpostsbynickname/';
  // private postSubscriptionsUrl = 'http://localhost:8081/api/posts/getpostsfromsubscriptions/';
  // private deletePostUrl = 'http://localhost:8081/api/posts/deletepost/';

  getPosts(page: number) {
    return this.http.get<Post[]>('/api/posts/getallposts/' + page);
  }

  getPostsByHashtagText(text: string, page: number) {
    return this.http.get<Post[]>('/api/hashtags/getpostsbyhashtag/' + text + "/" + page);
  }

  getPostsByNickname(nickname: string, page: number) {
    return this.http.get<Post[]>('/api/posts/getpostsbynickname/' + nickname + "/" + page);
  }

  deletePostById(postId: number, userId: number) {
     return this.http.delete<Post[]>('/api/posts/deletepost/' + postId + "/" + userId);
  }

  //Сделать везде ник или id
  getPostsFromSubscriptions(id: number, page: number) {
    return this.http.get<Post[]>('/api/posts/getpostsfromsubscriptions/' + id + "/" + page);
  }

}
