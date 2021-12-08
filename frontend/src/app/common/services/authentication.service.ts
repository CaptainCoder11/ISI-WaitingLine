import { Injectable, OnDestroy } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { flatMap, map, Observable, of, ReplaySubject, Subject } from 'rxjs';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { User } from '../models/user.model';

const LOCALSTORAGE = {
  USER: 'user'
}

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService implements OnDestroy {

  /* constants and variable declarations */
  private readonly apiUrl = environment.api_url;

  public ON_LOGIN: Subject<User> = new ReplaySubject<User>(1);
  public ON_LOGOUT: Subject<void> = new Subject<void>();


  constructor(private http: HttpClient, private router: Router) {
  }

  ngOnDestroy(): void {
    this.ON_LOGIN.complete();
    this.ON_LOGOUT.complete();
  }

  public get getUser(): User {
    return JSON.parse(localStorage.getItem(LOCALSTORAGE.USER));
  };

  private handleAuth(user: any) {
    this.ON_LOGIN.next(user);
  }

  public isAuthenticated(): boolean {
    return !!this.getUser;
  }

  public whoAmI(user: User): User {
    if (this.getUser) {
      return this.getUser;
    }

    this.handleAuth(user);
  }

  public login(user: User): Observable<any> {
    const url = `${this.apiUrl}/customer/login`;
    return this.http.post<User>(url, user);
  }

  public verifyOtp(user: User): Observable<any> {
    const url = `${this.apiUrl}/customer/verify`;

    return this.http.post<User>(url, user).pipe(
      map((user) => {
        localStorage.setItem('user', JSON.stringify(user));
        return of(this.whoAmI(user));
      }),
    );
  }

  public logout() {
    return localStorage.removeItem(LOCALSTORAGE.USER);
  }
}
