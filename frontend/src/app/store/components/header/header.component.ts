import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/common/services/authentication.service';
import { NotyUtil } from 'src/app/common/utils/noty-util';
import { Employee } from '../../models/employee.model';

@Component({
  selector: 'app-store-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class StoreHeaderComponent implements OnInit {
  constructor(public authService: AuthenticationService) {}

  ngOnInit(): void {}

  get isLoggedIn() {
    return this.authService.isAuthenticatedEmp();
  }

  get employee(): Employee {
    return this.authService.getEmployee;
  }

  onLogout() {
    this.authService.logoutEmp();
    NotyUtil.success('You are logged out!!!');
  }
}
