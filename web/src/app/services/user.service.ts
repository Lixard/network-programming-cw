import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';
import { ProfilePicture } from '../models/profile-picture';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}

  public getUsers(): Observable<User[]> {
    return this.http.get<User[]>('/api/users');
  }

  public getUser(userId: number): Observable<User> {
    return this.http.get<User>(`/api/users/${userId}`);
  }

  public updateProfilePicture(userId: number, image: File): Observable<ProfilePicture> {
    const formData = new FormData();
    formData.append('file', image);
    return this.http.put<ProfilePicture>(`/api/users/${userId}/avatar`, formData);
  }

  public downloadProfilePicture(userId: number): Observable<Blob> {
    return this.http.get<Blob>(`/api/users/${userId}/avatar`);
  }
}
