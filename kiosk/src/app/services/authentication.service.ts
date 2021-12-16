import { Injectable, OnDestroy } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable, of, ReplaySubject, Subject } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user.model';
import { StoreUser } from '../models/store-user.model';

const LOCALSTORAGE = {
  CUSTOMER: 'customer',
  STORE: 'store',
};

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService implements OnDestroy {
  /* constants and variable declarations */
  private readonly apiUrl = environment.api_url;

  public ON_LOGIN: Subject<User> = new ReplaySubject<any>(1);
  public ON_LOGOUT: Subject<void> = new Subject<void>();

  constructor(private http: HttpClient) { }

  ngOnDestroy(): void {
    this.ON_LOGIN.complete();
    this.ON_LOGOUT.complete();
  }

  public get getCustomer(): User {
    return JSON.parse(localStorage.getItem(LOCALSTORAGE.CUSTOMER)||"");
  }

  private handleAuth(user: any) {
    this.ON_LOGIN.next(user);
  }

  public isCustomerAuthenticated(): boolean {
    return !!this.getCustomer;
  }

  public whoAmI(user: any): any {
    if (this.getCustomer) {
      return this.getCustomer;
    }

    if (this.getStoreUser) {
      return this.getStoreUser;
    }

    this.handleAuth(user);
  }

  public storeLogin(user: StoreUser): Observable<any> {
    const url = `${this.apiUrl}/employee/login?email=${user.email}&password=${user.password}`;

    return this.http.post<User>(url, user).pipe(
      map((user) => {
        localStorage.setItem(LOCALSTORAGE.STORE, JSON.stringify(user));
        return of(this.whoAmI(user));
      })
    );
  }

  public storeLogout() {
    return localStorage.removeItem(LOCALSTORAGE.STORE);
  }

  public get getStoreUser(): any {
    const user=localStorage.getItem("store") || "";
    return JSON.parse(user);
  }

  public isStoreUserAuthenticated(): boolean {
    return !!this.getStoreUser;
  }
}
