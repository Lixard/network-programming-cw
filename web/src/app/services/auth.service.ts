import { APP_INITIALIZER, Injectable, Provider } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, ReplaySubject } from 'rxjs';
import { LoginData } from '../models/login-data.model';
import { CurrentUser, User } from '../models/user.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  readonly user$ = new ReplaySubject<CurrentUser>(1);

  constructor(private http: HttpClient) {}

  initialize(): Promise<void> {
    return new Promise<void>((resolve) => {
      this.loadProfile().subscribe((profile) => {
        this.user$.next(profile);
        resolve();
      });
    });
  }

  loadProfile(): Observable<CurrentUser> {
    return this.http.get<CurrentUser>(`/api/auth/this`);
  }

  login(data: LoginData): Observable<void> {
    const params = new HttpParams({
      fromObject: {
        username: data.username,
        password: data.password,
      },
    });

    const myHeaders = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
    });
    return this.http.post<void>('/api/auth/login', params.toString(), {
      headers: myHeaders,
    });
  }

  logout(): Observable<void> {
    return this.http.get<void>('/api/auth/logout');
  }

  register(user: User): Observable<void> {
    return this.http.post<void>('/api/users', user);
  }
}

export function loadCurrentUser(authService: AuthService): () => Promise<void> {
  return () => authService.initialize();
}

export const LOAD_CURRENT_USER_INITIALIZER: Provider = {
  provide: APP_INITIALIZER,
  useFactory: loadCurrentUser,
  multi: true,
  deps: [AuthService],
};
