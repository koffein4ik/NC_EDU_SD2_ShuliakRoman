import { Injectable } from '@angular/core';
import { StorageUserModel } from '../models/storageUserModel';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  private readonly TOKEN_KEY: string = "token";
  private readonly CURRENT_USER: string = "current_user";

  private currUser: StorageUserModel;

  public setToken(token: string): void {
    localStorage.setItem(this.TOKEN_KEY, token);
  }

  public setCurrentUser(currentUser: StorageUserModel): void {
    this.currUser = currentUser;
    localStorage.setItem(this.CURRENT_USER, JSON.stringify(currentUser));
  }

  public getCurrentUser(): StorageUserModel {
    return this.currUser || JSON.parse(localStorage.getItem(this.CURRENT_USER));
  }

  public getToken(): string {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  public clearToken(): void {
    localStorage.setItem(this.TOKEN_KEY, null);
  }
}
