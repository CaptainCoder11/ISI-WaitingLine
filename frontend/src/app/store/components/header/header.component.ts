import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/common/services/authentication.service';
import { DISPLAY_LOGO } from 'src/app/common/utils/functions';
import { NotyUtil } from 'src/app/common/utils/noty-util';

@Component({
  selector: 'app-store-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class StoreHeaderComponent implements OnInit {
  DISPLAY_LOGO = DISPLAY_LOGO;
  constructor(
    public authService: AuthenticationService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  get isLoggedIn() {
    return this.authService.isStoreUserAuthenticated();
  }

  get storeUser(): any {
    return this.authService.getStoreUser;
  }

  onLogout() {
    this.authService.storeLogout();
    NotyUtil.success('You are logged out!!!');
    this.router.navigate(['store', 'login']);
  }
}
