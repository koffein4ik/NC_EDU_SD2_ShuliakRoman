import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
 
@Injectable({
  providedIn: 'root'
})
export class AddpostService {

  constructor(private httpClient: HttpClient) { }

  postFile(fileToUpload: File[], userId: number, postDescription: string) {
    // const endpoint = 'http://localhost:8081/api/posts/addnewpost';
    const endpoint = '/api/posts/addnewpost/';
    const formData: FormData = new FormData();
    fileToUpload.forEach((file) => {
      formData.append('fileKey', file, file.name);
    });
    formData.append('userId', userId.toString());
    formData.append('postDescription', postDescription);
    return this.httpClient
      .post(endpoint, formData);
     // .map(() => { return true; })
     // .catch((e) => this.handleError(e));
  }
}
