import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Comment } from '../../models/comment';
import { Observable } from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient) { }

  // private apiUrl = 'http://localhost:8081/api/comments/getcommentsbypostid/';
  // private submitUrl = 'http://localhost:8081/api/comments/submitcomment';

  getCommentsByPostId(postId: number): Observable<Comment[]> {
    return this.http.get<Comment[]>('/api/comments/getcommentsbypostid/' + postId);
  }

  submitComment(postId: number, userId: number, text: string): Observable<Comment[]> {
    console.log(userId);
    var SubmitComment = {
      postId: postId,
      userId: userId,
      text: text
    }
    console.log(SubmitComment);
    return this.http.post<Comment[]>('/api/comments/submitcomment/', SubmitComment);
  }
}
