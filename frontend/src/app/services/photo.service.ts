import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PhotoService {

  constructor(private http: HttpClient) { }

  photourl = "/api/posts/image";

  getImage(): Observable<Blob> {
    return this.http.get(this.photourl, { responseType: 'blob'});
  }
}
