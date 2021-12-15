import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanActivateChild,
  Router,
  RouterStateSnapshot,
  UrlTree
} from '@angular/router';
import { Observable, of } from 'rxjs';
import { AuthenticationService } from '../../common/services/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class IsAuthenticatedStoreGuard implements CanActivate, CanActivateChild {
  constructor(private router: Router, private authService: AuthenticationService) {
  }

  /**
   * This method will check for authentication of user, whether user has access to page or not
   * @returns 
   */
  private check(): Observable<boolean | UrlTree> {
    if (this.authService.isCustomerAuthenticated()) {
      this.router.navigate(['']);
      return;
    }

    if (this.authService.isStoreUserAuthenticated())
      return of(true);
    else
      this.router.navigate(['/store', 'login']);
  }

  /**
   * This method will check for authentication of user, whether user has access to page or not
   * @returns 
   */
  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> {
    return this.check();
  }

  /**
   * This method will check for authentication of user, whether user has access to page or not
   * @returns 
   */
  canActivateChild(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> {
    return this.check();
  }
}
