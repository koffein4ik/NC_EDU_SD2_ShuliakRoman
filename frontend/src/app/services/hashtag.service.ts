import { Injectable } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HashtagService {

  constructor(private http: HttpClientModule) { }
}
