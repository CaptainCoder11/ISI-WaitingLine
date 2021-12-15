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
import _ from 'lodash';
import { ROLES } from 'src/app/common/models/roles.model';

@Injectable({
  providedIn: 'root'
})
export class CanVisitStoreGuard implements CanActivate, CanActivateChild {
  constructor(private router: Router) {
  }

  /**
   * 
   * @param route Checking if user can visit url/page
   * @returns 
   */
  private check(route: ActivatedRouteSnapshot): Observable<boolean | UrlTree> {
    if (!route.data) {
      return of(true);
    }


    const pageRoles: string[] = _.isArray(route.data["roles"])
      ? route.data["roles"]
      : [route.data["roles"]];

    const canVisit = _([ROLES.STOREOWNER, ROLES.EMPLOYEE])
      .some((role) => {
        return pageRoles.some(x => x == role);
      });


    if (canVisit) {
      return of(true);
    } else {
      const href = this.router.getCurrentNavigation().initialUrl.toString();
      return of(href === '/' ? this.router.createUrlTree([href]) : false);
    }
  }

  /**
   * 
   * @param route activating if user can visit url/page
   * @returns 
   */
  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> {
    return this.check(next);
  }

  /**
   * 
   * @param route activating if user can visit url/page
   * @returns 
   */
  canActivateChild(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> {
    return this.check(next);
  }
}
