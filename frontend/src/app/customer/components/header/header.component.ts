import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/common/models/user.model';
import { AuthenticationService } from 'src/app/common/services/authentication.service';
import { NotyUtil } from 'src/app/common/utils/noty-util';

@Component({
  selector: 'app-customer-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class CustomerHeaderComponent implements OnInit {

  constructor(public authService: AuthenticationService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  get isLoggedIn() {
    return this.authService.isAuthenticated();
  }

  get user(): User {
    return this.authService.getUser;
  }

  onLogout() {
    this.authService.logout();
    NotyUtil.success("You are logged out!!!");
    this.router.navigate(['/']);
  }
}
